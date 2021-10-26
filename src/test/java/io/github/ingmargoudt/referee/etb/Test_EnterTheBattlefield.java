package io.github.ingmargoudt.referee.etb;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.i.InspiringCleric;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_EnterTheBattlefield extends BaseGame {

    @Test
    void test_InspiringCleric() {
        Card inspiringCleric = new InspiringCleric();
        addCard(Zone.HAND, player1, inspiringCleric);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, inspiringCleric);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player1, 24);
    }
}
