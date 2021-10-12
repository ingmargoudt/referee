package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.abilities.*;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.cost.TapCost;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.zones.Battlefield;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Log4j2
public class Game {

    @Getter
    static final int STARTING_LIFE = 20;
    @Getter
    static final int STARTING_HAND_SIZE = 7;
    @Getter
    private final Stack stack;
    @Getter
    protected Battlefield battlefield;
    protected Player[] players = new Player[2];
    private UUID activePlayer;
    private UUID playerWithPriority;
    @Getter
    private boolean running;
    @Getter
    private int turnNumber = 1;
    @Getter
    private int stopAtTurn;
    @Getter
    private Phase stopAtPhase;
    @Getter
    private Turn currentTurn;

    public Game() {
        battlefield = new Battlefield();
        stack = new Stack(this);
    }

    public void addPlayer(Player player) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    public void stopAt(int turn, Phase phase) {
        stopAtTurn = turn;
        stopAtPhase = phase;
    }

    public void stop() {
        running = false;
    }


    public void start() {
        if (running) {
            return;
        }
        running = true;
        /*
        . 103.2
        After the starting player has been determined, each player shuffles their deck so that the cards
           are in a random order. Each player may then shuffle or cut their opponents’ decks. The players’
           decks become their libraries.
         */
        Arrays.stream(players).forEach(Player::shuffle);
        /*
        103.3. Each player begins the game with a starting life total of 20. Some variant games have different
        starting life totals
         */
        Arrays.stream(players).forEach(player -> player.setLife(STARTING_LIFE));
        /*
        103.4. Each player draws a number of cards equal to their starting hand size, which is normally seven.
         */
        Arrays.stream(players).forEach(player -> player.drawCard(STARTING_HAND_SIZE));
        Arrays.stream(players).forEach(Player::mulligan);
        activePlayer = players[0].getId();
        setPriority(activePlayer);
        while (running) {
            currentTurn = new Turn();
            currentTurn.run(this);
            turnNumber++;
        }
        applyContinuousEffects();
    }

    public Phase getCurrentPhase() {
        return currentTurn.getCurrentPhase();
    }

    public void putOnStack(Spell spell) {
        stack.putOnStack(spell);
    }

    public Optional<Player> getPlayer(UUID controller) {
        return Arrays.stream(players).filter(player -> player.getId().equals(controller)).findFirst();
    }

    public Optional<Player> getActivePlayer() {
        return Arrays.stream(players).filter(player -> player.getId().equals(activePlayer)).findFirst();
    }

    public Optional<Player> getPlayerWithPriority() {
        return Arrays.stream(players).filter(player -> player.getId().equals(playerWithPriority)).findFirst();
    }


    public void setPriority(UUID playerId) {
        playerWithPriority = playerId;
        getPlayer(playerWithPriority).ifPresent(player -> {

            EventBus.report(player.getName() + " gets priority");
            applyContinuousEffects();
            checkStateBasedActions();
        });
    }

    private void applyContinuousEffects() {
        battlefield.resetBase();
        EventBus.report("Applying continuous effects");
        //apply land mana abilities for the base card
        applyLandManaAbilities();
        applyStaticAbilities();
        //apply land mana abilities again for the current card
        applyLandManaAbilities();
    }

    private void applyStaticAbilities() {
        for (Permanent permanent : battlefield.getAll()) {
            for (Ability ability : permanent.getAbilities()) {
                if (ability instanceof StaticAbility) {
                    ability.resolve(permanent, this);
                }
            }
        }
    }

    private void applyLandManaAbilities() {
        EnumMap<SubType, Class<? extends Ability>> manaAbilities = new EnumMap<>(SubType.class);
        manaAbilities.put(SubType.MOUNTAIN, AddRedManaAbility.class);
        manaAbilities.put(SubType.PLAINS, AddWhiteManaAbility.class);
        manaAbilities.put(SubType.SWAMP, AddBlackManaAbility.class);
        for (Permanent permanent : battlefield.getAll()) {
            manaAbilities.forEach((subtype, manaAbility) -> {
                if (permanent.hasSubType(subtype) && !permanent.hasAbility(manaAbility)) {
                    try {
                        permanent.addAbility(manaAbility.getConstructor(Cost[].class).newInstance((Object) new Cost[]{new TapCost()}));
                    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        log.error(e.getMessage());
                    }
                }
            });
        }
    }

    private void checkStateBasedActions() {
        EventBus.report("Checking statebased actions");
        getBattlefield().getAll().forEach(permanent -> {
            if (permanent.getReceivedDamage() >= permanent.getToughness() && permanent.isCreature() && !permanent.hasAbility(Indestructible.class)) {
                permanent.destroy(this);
            }
        });
    }


    public void passPriority() {
        stack.pass(this, playerWithPriority);
        if (stack.allPlayersPassed()) {
            return;
        }
        if (players[0].getId().equals(playerWithPriority)) {
            setPriority(players[1].getId());
        } else {
            setPriority(players[0].getId());
        }


    }

    public void moveToBattlefield(Card card) {
        Permanent permanent = new Permanent(card);
        battlefield.add(permanent);
        applyContinuousEffects();
        raiseEvent(new EnterTheBattlefieldEvent(permanent));
    }

    public Event raiseEvent(Event event) {
        battlefield.getAll().forEach(permanent -> {
            permanent.getAbilities().forEach(ability -> {
                if (ability instanceof TriggeredAbility) {
                    TriggeredAbility triggeredAbility = (TriggeredAbility) ability;
                    if (triggeredAbility.checkTrigger(event, permanent)) {
                        stack.putOnStack(new StackAbility(triggeredAbility, permanent));
                    }
                }
            });
            permanent.getReplacementEffects().forEach(replacementEffect -> {
                replacementEffect.repondToEvent(this, event, permanent);
            });
        });
        return event;
    }


    public void moveToGraveyard(Card card) {
        getPlayer(card.getController()).ifPresent(controller -> {
            battlefield.remove(card);
            controller.putCardInGraveyard(card);

        });
    }


    public boolean isPlayable(Player player, Card card) {

        if (!player.getId().equals(playerWithPriority)) {
            return false;
        }
        if (!card.canBePlayed(this)) {
            return false;
        }
        if (!stack.isEmpty()) {
            if (card.isPermanent()) {
                return false;
            }
        }
        return true;
    }

    public List<Player> getPlayers() {
        return Arrays.asList(players);
    }


}
