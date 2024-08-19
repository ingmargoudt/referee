package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.a.AngelOfVitality;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_ScrollOfAvacyn extends BaseGame {

    @Test
    void withAngelInPlayGainLife(){
        Card scrollOfAvacyn = new ScrollOfAvacyn();
        Card angelOfVitality = new AngelOfVitality();
        addCard(Zone.BATTLEFIELD, player1, scrollOfAvacyn);
        addCard(Zone.BATTLEFIELD, player1, angelOfVitality);
        activateAbility(1, Phase.PRECOMBAT_MAINPHASE, player1, scrollOfAvacyn);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
    }

    @Test
    void withNoAngelInPlayGainNoLife(){
        Card scrollOfAvacyn = new ScrollOfAvacyn();
        addCard(Zone.BATTLEFIELD, player1, scrollOfAvacyn);
        activateAbility(1, Phase.PRECOMBAT_MAINPHASE, player1, scrollOfAvacyn);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 20);
    }
}
