package io.github.ingmargoudt.referee.cards.d;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.c.Cryoclasm;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.o.AlphaMyr;
import io.github.ingmargoudt.referee.cards.p.Plains;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_Devastate extends BaseGame {

    @Test
    void destroyPlains() {
        Card plains = new Plains();
        Card bears = new GrizzlyBears();
        Card devastate = new Devastate();
        Card alphaMyr = new AlphaMyr();
        addCard(Zone.BATTLEFIELD, player2, plains);
        addCard(Zone.HAND, player1, devastate);
        addCard(Zone.BATTLEFIELD,player1, bears);
        addCard(Zone.BATTLEFIELD,player2, alphaMyr);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, devastate, plains);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player2, plains);
        assertGraveyard(player2, alphaMyr);
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 1);
        assertLife(player2, 19);
        assertLife(player1, 19);
    }
}
