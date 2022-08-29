package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.abilities.*;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.zones.Battlefield;
import io.github.ingmargoudt.referee.game.zones.Zone;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;

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
    @Getter
    private Player activePlayer;
    @Getter
    private Player playerWithPriority;
    @Getter
    private boolean running;
    @Getter
    private int turnNumber = 0;
    @Getter
    private int stopAtTurn;
    @Getter
    private Phase stopAtPhase;
    @Getter
    private Turn currentTurn;

    @Getter
    private final Map<MagicObject, List<ContinuousEffect>> continuousEffects;

    public Game() {
        battlefield = new Battlefield();
        stack = new Stack(this);
        continuousEffects = new HashMap<>();
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

        while (running) {
            turnNumber++;
            currentTurn = new Turn();
            currentTurn.run(this);
        }
        applyContinuousEffects();
    }

    public void assignActivePlayer() {
        if (activePlayer == null) {
            activePlayer = players[0];
        } else {
            for (int i = 0; i < players.length; i++) {
                if (players[i].getId().equals(activePlayer.getId())) {
                    setActive(players[(i + 1) % players.length]);
                    break;
                }
            }
        }
        setPriority(activePlayer);
        EventBus.report(getActivePlayer() + " becomes active player");
    }

    private void setActive(Player theActivePlayer) {
        activePlayer = theActivePlayer;
    }

    public Phase getCurrentPhase() {
        return currentTurn.getCurrentPhase();
    }

    public void putOnStack(Spell spell) {
        stack.putOnStack(spell);
    }

    public void putOnStack(ActivatedAbility activatedAbility, MagicObject source) {
        stack.putOnStack(new StackAbility(activatedAbility, source));
    }

    public void setPriority(Player newPlayerWithPriority) {
        playerWithPriority = newPlayerWithPriority;
        EventBus.report(battlefield.getAll().stream().map(MagicObject::getName).collect(Collectors.joining()));


        EventBus.report(newPlayerWithPriority.getName() + " gets priority");
        applyContinuousEffects();
        checkStateBasedActions();

    }

    private void applyContinuousEffects() {
        battlefield.resetBase();
        EventBus.report("Applying continuous effects");
        //apply land mana abilities for the base card
        ManaAbilityApplier.run(this);
        applyStaticAbilities();
        //apply land mana abilities again for the current card
        ManaAbilityApplier.run(this);
    }

    private void applyStaticAbilities() {
        continuousEffects.forEach((source, effects) -> effects.forEach(effect -> effect.apply(source, this)));
        for (Permanent permanent : battlefield.getAll()) {
            for (Ability ability : permanent.getAbilities()) {
                if (ability instanceof StaticAbility) {
                    ability.resolve(permanent, this);
                }
            }
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
        if (players[0].getId().equals(playerWithPriority.getId())) {
            setPriority(players[1]);
        } else {
            setPriority(players[0]);
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
            permanent.getReplacementEffects().forEach(replacementEffect -> replacementEffect.repondToEvent(this, event, permanent));
        });
        return event;
    }


    public void moveToGraveyard(Card card) {
        battlefield.remove(card);
        card.getController().putCardInGraveyard(card);
    }

    public boolean isPlayable(Player player, ActivatedAbility ability, MagicObject magicObject) {
        if (!player.getId().equals(playerWithPriority.getId())) {
            return false;
        }
        return ability.canBePlayed(magicObject, this);
    }


    public boolean isPlayable(Player player, Card card) {

        if (!player.getId().equals(playerWithPriority.getId())) {
            return false;
        }
        if (!card.canBePlayed(this)) {
            return false;
        }
        if (!stack.isEmpty()) {
            return !card.isPermanent() && !card.isSorcery();
        }
        return true;
    }

    public List<Player> getPlayers() {
        return Arrays.asList(players);
    }


    public void addEffect(MagicObject source, ContinuousEffect continuousEffect) {
        continuousEffect.getDuration().setTurn(this.getTurnNumber() + continuousEffect.getDuration().getDurationType().getOffset());
        continuousEffects.putIfAbsent(source, new ArrayList<>());
        continuousEffects.get(source).add(continuousEffect);
    }

    public Zone locate(MagicObject object) {
        if (object instanceof Permanent) {
            if (getBattlefield().getAll().stream().anyMatch(p -> p.getId().equals(object.getId()))) {
                return Zone.BATTLEFIELD;
            }
        }
        Player controller = object.getController();
        if (object instanceof Card) {
            if (controller.getHand().getCards().contains(object)) {
                return Zone.HAND;
            }
        }
        return null;
    }
}
