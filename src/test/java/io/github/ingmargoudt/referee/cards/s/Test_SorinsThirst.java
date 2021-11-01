package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_SorinsThirst extends BaseGame {

    Card sorinsThirst = new SorinsThirst();
    Card bears = new GrizzlyBears();

    @Test
    void destroyandgainlife() {
        addCard(Zone.BATTLEFIELD, player1, bears);
        addCard(Zone.HAND, player1, sorinsThirst);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, sorinsThirst, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player1, bears);
        assertLife(player1, 22);
    }
}
