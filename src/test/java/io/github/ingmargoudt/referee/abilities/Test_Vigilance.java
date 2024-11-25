package io.github.ingmargoudt.referee.abilities;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.s.SerraAngel;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_Vigilance extends BaseGame {

    @Test
    void vigilanceDoesNotCauseTapDuringDeclareAttackers(){
        Card serraAngel = new SerraAngel();
        addCard(Zone.BATTLEFIELD, player1, serraAngel);
        attack(player1, 3, serraAngel);
        stopAt(3, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertUntapped(player1, serraAngel);
    }
}
