package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_SheoldredTheApocalypse extends BaseGame {

    @Test
    void sheoldredGainLifeOnDraw(){
        Card sheoldred = new SheoldredTheApocalypse();
        addCard(Zone.BATTLEFIELD, player1, sheoldred);
        stopAt(3, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 24);
    }
}
