package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.l.LightningBolt;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_CounterSpell extends BaseGame {

    @Test
    void castSpellWithSingleTargetSpell() {
        Card bolt = new LightningBolt();
        Card counterspell = new CounterSpell();
        addCard(Zone.HAND, player1, bolt);
        addCard(Zone.HAND, player2, counterspell);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, player2);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, counterspell, bolt);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player1, bolt);
    }
}
