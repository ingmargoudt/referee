package io.github.ingmargoudt.referee.cards.a;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.i.InspiringCleric;
import io.github.ingmargoudt.referee.cards.s.SoulWarden;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_AjanisPridemate extends BaseGame {


    @Test
    void p1p1WhenGainLife() {

        var pridemate = new AjanisPridemate();
        var inspiringCleric = new InspiringCleric();
        // Arrange
        addCard(Zone.BATTLEFIELD, player1, pridemate);
        addCard(Zone.HAND, player1, inspiringCleric);
        // Act
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, inspiringCleric);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
start();
        // Assert
        assertPermanentPower(Zone.BATTLEFIELD, player1,pridemate,3);
    }

    @Test
    void nop1p1WhenNoLifeGain() {

        var pridemate = new AjanisPridemate();
        var inspiringCleric = new InspiringCleric();
        // Arrange
        addCard(Zone.BATTLEFIELD, player1, pridemate);
        addCard(Zone.HAND, player2, inspiringCleric);
        // Act
        castSpell(2, Phase.PRECOMBAT_MAINPHASE, player2, inspiringCleric);

        stopAt(2, Phase.PRECOMBAT_MAINPHASE);
        start();
        // Assert
        assertPermanentPower(Zone.BATTLEFIELD, player1,pridemate,2);

        }

        @Test
    void ajanisPridemateWithSoulwarden() {

            var pridemate = new AjanisPridemate();
            var soulwarden = new SoulWarden();
            // Arrange
            addCard(Zone.HAND, player1, pridemate);
            addCard(Zone.BATTLEFIELD, player1, soulwarden);
            // Act
            castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, pridemate);

            stopAt(1, Phase.PRECOMBAT_MAINPHASE);
            start();

            // Assert
            assertPermanentPower(Zone.BATTLEFIELD, player1, pridemate, 3);
        }

}
