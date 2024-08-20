package io.github.ingmargoudt.referee.abilities;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.b.BrightbladeStoat;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.s.ShivanDragon;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_Firststrike extends BaseGame {

    @Test
    void FirststrikeBeforeRegularDamageUnblocked(){
        Card brightbladeStoat = new BrightbladeStoat();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, brightbladeStoat);
        addCard(Zone.BATTLEFIELD, player1, bears);
        attack(player1, 1, brightbladeStoat);
        attack(player1, 1, bears);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player2, 16);
    }

    @Test
    void FirststrikeBeforeRegularDamageBlocked(){
        Card brightbladeStoat = new BrightbladeStoat();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, brightbladeStoat);
        addCard(Zone.BATTLEFIELD, player2, bears);
        attack(player1, 1, brightbladeStoat);
        block(player2, 1, bears, brightbladeStoat);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player2, 20);
        assertGraveyard(player2, bears);
        assertPermanent(Zone.BATTLEFIELD, player1, brightbladeStoat, 1);
    }

    @Test
    void FirststrikeBlockedByFirstStrike(){
        Card brightbladeStoat = new BrightbladeStoat();
        Card brightbladeStoat2 = new BrightbladeStoat();
        addCard(Zone.BATTLEFIELD, player1, brightbladeStoat);
        addCard(Zone.BATTLEFIELD, player2, brightbladeStoat2);
        attack(player1, 1, brightbladeStoat);
        block(player2, 1, brightbladeStoat2, brightbladeStoat);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player2, 22);
        assertLife(player1, 22);
        assertGraveyard(player1, brightbladeStoat);
        assertGraveyard(player2, brightbladeStoat2);
    }

    @Test
    void FirststrikeBlockedByBiggerCreature(){
        Card brightbladeStoat = new BrightbladeStoat();
        Card shivanDragon = new ShivanDragon();
        addCard(Zone.BATTLEFIELD, player1, brightbladeStoat);
        addCard(Zone.BATTLEFIELD, player2, shivanDragon);
        attack(player1, 1, brightbladeStoat);
        block(player2, 1, shivanDragon, brightbladeStoat);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player1, 22);
        assertGraveyard(player1, brightbladeStoat);
        assertPermanent(Zone.BATTLEFIELD, player2, shivanDragon, 1);
    }
}
