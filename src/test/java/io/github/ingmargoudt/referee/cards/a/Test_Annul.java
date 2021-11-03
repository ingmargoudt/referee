package io.github.ingmargoudt.referee.cards.a;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GloriousAnthem;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.o.AlphaMyr;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_Annul extends BaseGame {


    @Test
    void counterEnchantmentSpell() {
        Card gloriousAnthem = new GloriousAnthem();
        Card annul = new Annul();
        addCard(Zone.HAND, player1, gloriousAnthem);
        addCard(Zone.HAND, player2, annul);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, gloriousAnthem);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, annul, gloriousAnthem);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player1, gloriousAnthem);
    }

    @Test
    void counterArtifactSpell() {
        Card alphaMyr = new AlphaMyr();
        Card annul = new Annul();
        addCard(Zone.HAND, player1, alphaMyr);
        addCard(Zone.HAND, player2, annul);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, alphaMyr);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, annul, alphaMyr);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player1, alphaMyr);
    }

    @Test
    void canNotCounterCreatureSpell() {
        Card bears = new GrizzlyBears();
        Card annul = new Annul();
        addCard(Zone.HAND, player1, bears);
        addCard(Zone.HAND, player2, annul);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, annul, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        Throwable throwable = assertThrows(AssertionError.class, this::start);
        assertThat(throwable.getMessage()).isEqualTo("Player 2 has remaining actions: Cast Annul targeting Grizzly Bears");
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 1);
    }
}
