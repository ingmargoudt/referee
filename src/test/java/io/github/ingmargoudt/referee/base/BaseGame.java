package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.framework.ConsoleListener;
import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.framework.InputBus;
import io.github.ingmargoudt.referee.framework.TestInputListener;
import io.github.ingmargoudt.referee.game.*;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseGame {

    protected TestPlayer player1;
    protected TestPlayer player2;
    private TestGame game;
    private TestInputListener inputListener;

    private Library createLibraries() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            cards.add(new Card("Mountain"));
        }
        return new Library(cards);
    }

    @BeforeEach
    public void before(TestInfo testInfo) {
        EventBus.report("Starting " + testInfo.getTestMethod());
    }


    public BaseGame() {
        inputListener = new TestInputListener();
        game = new TestGame();
        player1 = new TestPlayer("Player 1", game, createLibraries());
        player2 = new TestPlayer("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());
        InputBus.registerListener(inputListener);

    }

    public void castSpell(int turn, Phase phase, TestPlayer player, Card card) {
        player.addAction(new CastSpellAction(turn, phase, card));
    }

    public void stopAt(int turn, Phase phase) {
        game.stopAt(turn, phase);
    }

    public void start() {
        game.start();
        boolean ok = true;
        if (player1.hasRemainingActions()) {
            EventBus.report(player1.getName() + " has remaining actions");
            ok = false;
        }
        if (player2.hasRemainingActions()) {
            EventBus.report(player2.getName() + " has remaining actions");
            ok = false;
        }
        if (!ok) {
            Fail.fail("Not all actions executed");
        }
    }

    public void addCard(Zone zone, TestPlayer player, Card card, int i) {
        card.setController(player.getId());
        if (zone == Zone.HAND) {
            player.getHand().addCard(card);
        }
        if (zone == Zone.BATTLEFIELD) {
            game.getBattlefield().add(new Permanent(card));
        }
    }

    public void assertPermanent(Zone zone, TestPlayer player, Card theCard, int i) {
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

    public void assertPermanentPower(Zone zone, TestPlayer player, Card theCard, int power) {
        if (zone == Zone.BATTLEFIELD) {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.getName().equals(theCard.getName()) && permanent.isControlledBy(player)) {
                    assertThat(permanent.getPower()).isEqualTo(power);
                }

            }
        }

    }

    public void assertPermanentToughness(Zone zone, TestPlayer player, Card theCard, int toughness) {
        if (zone == Zone.BATTLEFIELD) {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.getName().equals(theCard.getName()) && permanent.isControlledBy(player)) {
                    assertThat(permanent.getToughness()).isEqualTo(toughness);
                }

            }
        }

    }

    public void addCommand(UUID uuid, String command) {
        inputListener.addInput(uuid, command);
    }
}
