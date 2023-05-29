package io.github.ingmargoudt.referee.cards.t;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.i.Island;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_TempestDjinn extends BaseGame {

    @Test
    void boostedByIslands() {
        Card tempestDjinn = new TempestDjinn();
        int numIslands = 3;
        for (int i = 0; i < numIslands; i++) {
            addCard(Zone.BATTLEFIELD, player1, new Island());
        }
        addCard(Zone.BATTLEFIELD, player2, new Island());
        addCard(Zone.BATTLEFIELD, player1, tempestDjinn);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, tempestDjinn, numIslands);
    }

    @Test
    void notBoostedByOtherPlayersIslands() {
        Card tempestDjinn = new TempestDjinn();
        int numIslands = 3;
        for (int i = 0; i < numIslands; i++) {
            addCard(Zone.BATTLEFIELD, player2, new Island());
        }
        addCard(Zone.BATTLEFIELD, player1, tempestDjinn);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, tempestDjinn, 0);
    }
}
