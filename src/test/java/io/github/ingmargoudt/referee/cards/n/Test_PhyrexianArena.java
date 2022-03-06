package io.github.ingmargoudt.referee.cards.n;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.p.PhyrexianArena;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_PhyrexianArena extends BaseGame {

    @Test
    void drawCardAndLoseLife(){
        Card arena = new PhyrexianArena();
        addCard(Zone.BATTLEFIELD, player1, arena);
        stopAt(2, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 19);
    }
}
