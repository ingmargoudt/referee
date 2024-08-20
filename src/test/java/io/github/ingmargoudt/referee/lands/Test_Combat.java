package io.github.ingmargoudt.referee.lands;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_Combat extends BaseGame {

    @Test
    void test_attack(){
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, bears);
        attack(player1, 1, bears);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player2, 18);
    }
}
