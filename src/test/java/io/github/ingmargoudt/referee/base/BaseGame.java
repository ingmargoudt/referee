package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.framework.ConsoleListener;
import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.zones.Library;
import io.github.ingmargoudt.referee.game.zones.Zone;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class BaseGame {

    protected TestPlayer player1;
    protected TestPlayer player2;
    private TestGame game;

    private Library createLibraries() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            cards.add(new Card("Mountain"));
        }
        return new Library(cards);
    }

    @BeforeEach
    public void before(TestInfo testInfo) {
        testInfo.getTestMethod().ifPresent(method -> {
            log.info("*********************************");
            log.info("Starting " + method.getName());
            log.info("*********************************");
        });

    }
    @AfterEach
    public void after(){
        log.info("");
    }


    public BaseGame() {
        game = new TestGame();
        player1 = new TestPlayer("Player 1", game, createLibraries());
        player2 = new TestPlayer("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());

    }

    public void castSpell(int turn, Phase phase, TestPlayer player, Card card) {
        player.addAction(new CastSpellAction(turn, phase, card));
    }

    protected void disablePlayerActionLogging() {
        EventBus.clear();
    }

    public void stopAt(int turn, Phase phase) {
        game.stopAt(turn, phase);
    }

    public void start() {
        game.start();
        boolean ok = true;
        if (player1.hasRemainingActions()) {
            log.error(player1.getName() + " has remaining actions");
            ok = false;
        }
        if (player2.hasRemainingActions()) {
            log.error(player2.getName() + " has remaining actions");
            ok = false;
        }
        if (!ok) {
            Fail.fail("Not all actions executed");
        }
    }

    public void addCard(Zone zone, TestPlayer player, Card card, int amount) {
        card.setController(player.getId());
        for(int i = 0; i<amount; i++) {
            if (zone == Zone.HAND) {
                player.getHand().addCard(card);
            }
            if (zone == Zone.BATTLEFIELD) {

                game.getBattlefield().add(new Permanent(card));
            }
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
    public void assertPermanentHasAbility(TestPlayer thePlayer, Card theCard, Class<? extends Ability> theAbility){
        for(Permanent permanent : game.getBattlefield().getAll()){
            if(permanent.isControlledBy(thePlayer) && permanent.getName().equals(theCard.getName())){
                assertThat(permanent.hasAbility(theAbility));
                log.info(theCard.getName() + " of "+thePlayer.getName() + " has the "+theAbility.getSimpleName());
            }
        }
    }


}
