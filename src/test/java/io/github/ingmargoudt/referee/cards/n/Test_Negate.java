package io.github.ingmargoudt.referee.cards.n;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.l.LightningBolt;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Test_Negate extends BaseGame {

    @Test
    void counterNonCreatureSpell() {
        Card bolt = new LightningBolt();
        Card negate = new Negate();
        addCard(Zone.HAND, player1, bolt);
        addCard(Zone.HAND, player2, negate);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, player2);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, negate, bolt);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player2, 20);
    }

    @Test
    void canNotcounterNotNonCreatureSpell() {
        Card bears = new GrizzlyBears();
        Card negate = new Negate();
        addCard(Zone.HAND, player1, bears);
        addCard(Zone.HAND, player2, negate);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, negate, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        Throwable throwable = assertThrows(AssertionError.class, this::start);
        assertThat(throwable.getMessage()).isEqualTo("Player 2 has remaining actions: Cast Negate targeting Grizzly Bears");
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 1);
    }
}
