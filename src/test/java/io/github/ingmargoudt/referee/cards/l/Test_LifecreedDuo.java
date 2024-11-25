package io.github.ingmargoudt.referee.cards.l;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GloriousAnthem;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_LifecreedDuo extends BaseGame {

    @Test
    void triggerGainLife() {
        Card lifecreedDuo = new LifecreedDuo();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, lifecreedDuo);
        addCard(Zone.HAND, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 21);
    }

    @Test
    void triggerGainLifeTwice() {
        Card lifecreedDuo = new LifecreedDuo();
        Card otherLifecreedDuo = new LifecreedDuo();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, lifecreedDuo);
        addCard(Zone.BATTLEFIELD, player1, otherLifecreedDuo);
        addCard(Zone.HAND, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 22);
    }

    @Test
    void notTriggerOfNonCreature() {
        Card lifecreedDuo = new LifecreedDuo();
        Card anthem = new GloriousAnthem();
        addCard(Zone.BATTLEFIELD, player1, lifecreedDuo);
        addCard(Zone.HAND, player1, anthem);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, anthem);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 20);
    }
}
