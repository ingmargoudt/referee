package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.m.MoxSapphire;
import io.github.ingmargoudt.referee.cards.o.AlphaMyr;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_SoulWarden extends BaseGame {

    @Test
    void soulwardenGainLifeOnCreatureEnter() {
        var soulwarden = new SoulWarden();
        var alphaMyr = new AlphaMyr();
        // Arrange
        addCard(Zone.BATTLEFIELD, player1, soulwarden);
        // Act
        addCard(Zone.HAND, player1, alphaMyr);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, alphaMyr);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        // Assert
        assertLife(player1, 21);
    }

    @Test
    void soulwardenGainLifeOnCreatureEnterOpponent() {
        var soulwarden = new SoulWarden();
        var alphaMyr = new AlphaMyr();
        // Arrange
        addCard(Zone.BATTLEFIELD, player1, soulwarden);
        // Act
        addCard(Zone.BATTLEFIELD, player2, alphaMyr);

        castSpell(2, Phase.PRECOMBAT_MAINPHASE, player2, alphaMyr);

        stopAt(2, Phase.PRECOMBAT_MAINPHASE);
        start();
        // Assert
        assertLife(player1, 20);
    }

    @Test
    void soulwardenGainLifeOnCreatureEnterMultiple() {
        var soulwarden = new SoulWarden();
        var alphaMyr = new AlphaMyr();
        var alphaMyr2 = new AlphaMyr();
        // Arrange
        addCard(Zone.BATTLEFIELD, player1, soulwarden);
        // Act
        addCard(Zone.HAND, player1, alphaMyr);
        addCard(Zone.HAND, player1, alphaMyr2);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, alphaMyr);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, alphaMyr2);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        // Assert
        assertLife(player1, 22);
    }

    @Test
    void soulwardenNoGainLifeOnNoncreatureEnter() {
        var soulwarden = new SoulWarden();
        var moxSapphire = new MoxSapphire();
        // Arrange
        addCard(Zone.BATTLEFIELD, player1, soulwarden);
        // Act
        addCard(Zone.HAND, player1, moxSapphire);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, moxSapphire);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        // Assert
        assertLife(player1, 20);
    }
}
