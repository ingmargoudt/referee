package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_SinewSliver extends BaseGame {

    @Test
    void boostOwnSliver() {
        Card sinewSliver = new SinewSliver();
        addCard(Zone.BATTLEFIELD, player1, sinewSliver);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, sinewSliver, 2);
    }

    @Test
    void boostAllSliver() {
        Card sinewSliver = new SinewSliver();
        Card sinewSliver2 = new SinewSliver();
        addCard(Zone.BATTLEFIELD, player1, sinewSliver);
        addCard(Zone.BATTLEFIELD, player2, sinewSliver2);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, sinewSliver, 3);
        assertPermanentPower(Zone.BATTLEFIELD, player2, sinewSliver, 3);
    }

    @Test
    void doNotBoostOwnGrizzlyBears() {
        Card sinewSliver = new SinewSliver();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, sinewSliver);
        addCard(Zone.BATTLEFIELD, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 2);
    }
}
