package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.framework.Console;
import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.SubTypes;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.game.zones.Library;
import io.github.ingmargoudt.referee.game.zones.Zone;
import io.github.ingmargoudt.referee.players.Player;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class BaseGame {

    protected TestPlayer player1;
    protected TestPlayer player2;
    private TestGame game;

    private Map<UUID, Integer> startingLifes = new HashMap<>();

    public BaseGame() {
        game = new TestGame();
        player1 = new TestPlayer("Player 1", game, createLibraries());
        player2 = new TestPlayer("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new Console());
    }

    private Library createLibraries() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            cards.add(new Card("Mountain"));
        }
        return new Library(cards);
    }

    @BeforeEach
    void before(TestInfo testInfo) {
        testInfo.getTestMethod().ifPresent(method -> {
            log.info("*********************************");
            log.info("Starting " + method.getName());
            log.info("*********************************");
        });

    }

    @AfterEach
    void after() {
        log.info("");
    }

    protected void castSpell(int turn, Phase phase, TestPlayer player, Card card) {
        player.addAction(new CastSpellAction(turn, phase, card));
    }

    protected void activateAbility(int turn, Phase phase, TestPlayer player, Card card) {
        Optional<Permanent> p = findPermanentByCard(player, card);
        if (p.isPresent()) {
            Permanent permanent = p.get();
            Iterator<Ability> iterator = permanent.getAbilities().iterator();
            while (iterator.hasNext()) {
                Ability ability = iterator.next();
                if (ability instanceof ActivatedAbility) {
                    player.addAction(new ActivateAbilityAction(1, phase, (ActivatedAbility) ability, permanent));
                    return;
                }
            }
        } else {
            Iterator<Ability> cardAbilities = card.getAbilities().iterator();
            while (cardAbilities.hasNext()) {
                Ability ability = cardAbilities.next();
                if (ability instanceof ActivatedAbility) {
                    player.addAction(new ActivateAbilityAction(1, phase, (ActivatedAbility) ability, card));
                    return;
                }
            }
        }
    }

    protected void activateAbility(int turn, Phase phase, TestPlayer player, Card card, Targetable targetable) {
        Optional<Permanent> p = findPermanentByCard(player, card);
        if (p.isPresent()) {
            Permanent permanent = p.get();
            Iterator<Ability> iterator = permanent.getAbilities().iterator();
            while (iterator.hasNext()) {
                Ability ability = iterator.next();
                if (ability instanceof ActivatedAbility) {
                    player.addAction(new ActivateAbilityAction(turn, phase, (ActivatedAbility) ability, permanent, targetable));
                    return;
                }
            }
        } else {
            Iterator<Ability> cardAbilities = card.getAbilities().iterator();
            while (cardAbilities.hasNext()) {
                Ability ability = cardAbilities.next();
                if (ability instanceof ActivatedAbility) {
                    player.addAction(new ActivateAbilityAction(turn, phase, (ActivatedAbility) ability, card, targetable));
                    return;
                }
            }
        }
    }


    protected void castSpell(int turn, Phase phase, TestPlayer player, Card card, Targetable targetable) {
        player.addAction(new CastSpellAction(turn, phase, card, targetable));
    }


    protected void playLand(TestPlayer player, int turnNumber, Phase phase, Card card) {
        player.addAction(new PlayLandAction(turnNumber, phase, card));
    }

    protected void disablePlayerActionLogging() {
        EventBus.clear();
    }

    protected void stopAt(int turn, Phase phase) {
        game.stopAt(turn, phase);
    }

    protected void start() {
        game.start();

        boolean ok = true;
        String message = "";
        if (player1.hasRemainingActions()) {
            message += (player1.getName() + " has remaining actions: " + player1.getActions().stream().map(Object::toString).collect(Collectors.joining()));
        }
        if (player2.hasRemainingActions()) {
            message += (player2.getName() + " has remaining actions: " + player2.getActions().stream().map(Object::toString).collect(Collectors.joining()));
        }
        if (!message.isEmpty()) {
            Fail.fail(message);
        }
    }

    protected void addCard(Zone zone, TestPlayer player, Card card) {
        addCard(zone, player, card, 1);
    }

    protected void addCard(Zone zone, TestPlayer player, Card card, int amount) {
        card.setController(player);
        card.setOwner(player);
        for (int i = 0; i < amount; i++) {
            if (zone == Zone.HAND) {
                player.getHand().addCard(card);
            }
            if (zone == Zone.BATTLEFIELD) {

                game.getBattlefield().add(new Permanent(card));
            }
        }
    }

    protected void assertPermanent(Zone zone, TestPlayer player, Card theCard, int i) {
        int amount = 0;
        if (zone == Zone.BATTLEFIELD) {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.getName().equals(theCard.getName()) && permanent.isControlledBy(player)) {
                    amount++;
                }

            }
        }
        assertThat(amount).as("Wrong number of %s controlled by %s, expected %s, got %s", theCard.getName(), player.getName(), i, amount).isEqualTo(i);

    }

    protected void assertPermanentPower(Zone zone, TestPlayer player, Card theCard, int power) {
        if (zone == Zone.BATTLEFIELD) {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.getName().equals(theCard.getName()) && permanent.isControlledBy(player) && permanent.getBase().getId().equals(theCard.getId())) {
                    assertThat(permanent.getPower()).isEqualTo(power);
                    log.info(theCard.getName() + " with power " + power + " found under control of player " + player.getName());
                }

            }
        }

    }

    protected void assertPermanentToughness(Zone zone, TestPlayer player, Card theCard, int toughness) {
        if (zone == Zone.BATTLEFIELD) {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.getName().equals(theCard.getName()) && permanent.isControlledBy(player) && permanent.getBase().getId().equals(theCard.getId())) {
                    assertThat(permanent.getToughness()).isEqualTo(toughness);
                    log.info(theCard.getName() + " with toughness " + toughness + " found under control of player " + player.getName());
                }

            }
        }

    }

    protected void assertPermanentHasAbility(TestPlayer thePlayer, Card theCard, Class<? extends
            Ability> theAbility) {
        for (Permanent permanent : game.getBattlefield().getAll()) {
            if (permanent.isControlledBy(thePlayer) && permanent.getName().equals(theCard.getName())) {
                assertThat(permanent.hasAbility(theAbility)).as(theCard.getName() + " does not have the " + theAbility.getSimpleName()).isTrue();
                log.info(theCard.getName() + " of " + thePlayer.getName() + " has the " + theAbility.getSimpleName());
            }
        }
    }

    protected void assertAbilityCount(TestPlayer thePlayer, Card theCard, int count) {
        for (Permanent permanent : game.getBattlefield().getAll()) {
            if (permanent.isControlledBy(thePlayer) && permanent.getName().equals(theCard.getName())) {
                assertThat(permanent.getAbilities().count() + permanent.getReplacementEffects().size()).isEqualTo(count);
            }
        }
    }

    protected void assertLife(TestPlayer testPlayer, int amount) {
        assertThat(testPlayer.getLife()).isEqualTo(amount);
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }


    protected void assertTapped(TestPlayer thePlayer, Card theCard) {
        for (Permanent permanent : game.getBattlefield().getAll()) {
            if (permanent.isControlledBy(thePlayer) && permanent.getName().equals(theCard.getName())) {
                assertThat(permanent.isTapped()).as(permanent.getName() + " is expected to be tapped").isTrue();
                log.info(theCard.getName() + " of " + thePlayer.getName() + " is tapped");
                return;
            }
        }
        Fail.fail("No permanent named " + theCard.getName() + " under control of " + thePlayer);
    }

    protected void assertUntapped(TestPlayer thePlayer, Card theCard) {
        for (Permanent permanent : game.getBattlefield().getAll()) {
            if (permanent.isControlledBy(thePlayer) && permanent.getName().equals(theCard.getName())) {
                assertThat(permanent.isTapped()).as(permanent.getName() + " is expected to be untapped").isFalse();
                log.info(theCard.getName() + " of " + thePlayer.getName() + " is untapped");
                return;
            }
        }
        Fail.fail("No permanent named " + theCard.getName() + " under control of " + thePlayer);
    }


    protected void assertSubTypes(TestPlayer thePlayer, Card theCard, SubTypes thesubtypes) {
        for (Permanent permanent : game.getBattlefield().getAll()) {
            if (permanent.isControlledBy(thePlayer) && permanent.getName().equals(theCard.getName())) {
                assertThat(permanent.getSubTypes()).as(permanent.getName() + " has wrong set of subtypes").isEqualTo(thesubtypes);
                log.info(theCard.getName() + " of " + thePlayer.getName() + " has subtypes " + thesubtypes);
                return;
            }
        }
        Fail.fail("No permanent named " + theCard.getName() + " under control of " + thePlayer);

    }

    protected void setOption(TestPlayer testPlayer, String option) {
        testPlayer.addOption(option);
    }

    private Optional<Permanent> findPermanentByCard(TestPlayer thePlaer, Card theCard) {
        return game.getBattlefield().getAll().stream().filter(permanent -> permanent.isControlledBy(thePlaer) && permanent.getName().equals(theCard.getName())).findFirst();
    }

    protected void assertGraveyard(TestPlayer player, Card theCard) {
        assertThat(player.getGraveyard().contains(theCard)).isTrue();
    }

    protected void setLife(TestPlayer player, int lifeTotal) {
        player.addAction(new TestSetLifeAction(1, Phase.PRECOMBAT_MAINPHASE, lifeTotal));
    }

    protected void assertActivePlayer(TestPlayer testPlayer) {
            assertThat(game.getActivePlayer().getId()).isEqualTo(testPlayer.getId());

    }
}
