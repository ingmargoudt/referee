package io.github.ingmargoudt.referee.cards.g;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_GloriousAnthem extends BaseGame {

    Card bears = new GrizzlyBears();
    Card gloriousAnthem = new GloriousAnthem();

    @Test
    void gloriousAnthemBoosts() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, bears, 1);
        addCard(Zone.BATTLEFIELD, player1, gloriousAnthem, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 3);
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 3);
    }

    @Test
    void gloriousAnthemNotBoostsOtherPlayersCreature() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player2, bears, 1);
        addCard(Zone.BATTLEFIELD, player1, gloriousAnthem, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player2, bears, 2);
        assertPermanentToughness(Zone.BATTLEFIELD, player2, bears, 2);
    }
}
