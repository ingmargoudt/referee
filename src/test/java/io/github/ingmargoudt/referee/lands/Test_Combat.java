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
        Card bears2 = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, bears);
        addCard(Zone.BATTLEFIELD, player2, bears2);
        attack(player1, 1, bears);
        block(player2, 1, bears2, bears);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertLife(player2, 20);
        assertGraveyard(player1, bears);
        assertGraveyard(player2, bears2);
    }
}
