package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_SinisterMonolith extends BaseGame {

    @Test
    void gainLife(){
        Card sinisterMonolith = new SinisterMonolith();
        addCard(Zone.BATTLEFIELD, player1, sinisterMonolith);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player1, 21);
        assertLife(player2, 19);
    }

    @Test
    void doNotGainLifeOnOtherTurn(){
        Card sinisterMonolith = new SinisterMonolith();
        addCard(Zone.BATTLEFIELD, player1, sinisterMonolith);
        stopAt(2, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player1, 21);
        assertLife(player2, 19);
    }
}
