package io.github.ingmargoudt.referee.abilities;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.d.DarksteelMyr;
import io.github.ingmargoudt.referee.cards.l.LightningBolt;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_Indestructible extends BaseGame {

    @Test
    void indestructible_does_not_die_from_lethal_damage() {
        Card bolt = new LightningBolt();
        Card darksteelmyr = new DarksteelMyr();
        addCard(Zone.HAND, player1, bolt);
        addCard(Zone.BATTLEFIELD, player2, darksteelmyr);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, darksteelmyr);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player2, 20);
        assertPermanent(Zone.BATTLEFIELD, player2, darksteelmyr, 1);
    }
}
