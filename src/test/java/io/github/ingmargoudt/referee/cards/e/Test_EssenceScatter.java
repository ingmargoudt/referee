package io.github.ingmargoudt.referee.cards.e;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.l.LightningBolt;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_EssenceScatter extends BaseGame {

    @Test
    void cannotCounterNonCreatureSpell() {
        Card bolt = new LightningBolt();
        Card essenceScatter = new EssenceScatter();
        addCard(Zone.HAND, player1, bolt);
        addCard(Zone.HAND, player2, essenceScatter);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, player2);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, essenceScatter, bolt);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        Throwable throwable = assertThrows(AssertionError.class, this::start);
        assertThat(throwable.getMessage()).isEqualTo("Player 2 has remaining actions: Cast Essence Scatter targeting Lightning Bolt");
        assertLife(player2, 17);
    }

    @Test
    void counterCreatureSpell() {
        Card bears = new GrizzlyBears();
        Card essenceScatter = new EssenceScatter();
        addCard(Zone.HAND, player1, bears);
        addCard(Zone.HAND, player2, essenceScatter);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, essenceScatter, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player1 , bears);
    }
}
