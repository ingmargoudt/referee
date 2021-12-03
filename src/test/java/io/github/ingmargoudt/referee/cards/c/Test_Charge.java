package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_Charge extends BaseGame {


    @Test
    void gloriousAnthemBoosts() {
        Card bears = new GrizzlyBears();
        Card charge = new Charge();
        addCard(Zone.BATTLEFIELD, player1, bears, 1);
        addCard(Zone.HAND, player1, charge, 1);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, charge);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 3);
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 3);
    }
}
