package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.f.Forest;
import io.github.ingmargoudt.referee.cards.i.Island;
import io.github.ingmargoudt.referee.cards.p.Plains;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Test_Cryoclasm extends BaseGame {

    @Test
    void destroyPlains() {
        Card plains = new Plains();
        Card cryoclasm = new Cryoclasm();
        addCard(Zone.BATTLEFIELD, player2, plains);
        addCard(Zone.HAND, player1, cryoclasm);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, cryoclasm, plains);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player2, plains);
        assertLife(player2, 17);
    }


    @Test
    void destroyIsland() {
        Card island = new Island();
        Card cryoclasm = new Cryoclasm();
        addCard(Zone.BATTLEFIELD, player2, island);
        addCard(Zone.HAND, player1, cryoclasm);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, cryoclasm, island);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertGraveyard(player2, island);
        assertLife(player2, 17);
    }

    @Test
    void doNotDestroyForest() {
        Card forest = new Forest();
        Card cryoclasm = new Cryoclasm();
        addCard(Zone.BATTLEFIELD, player2, forest);
        addCard(Zone.HAND, player1, cryoclasm);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, cryoclasm, forest);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        Throwable throwable = assertThrows(AssertionError.class, this::start);
        assertThat(throwable.getMessage()).isEqualTo("Player 1 has remaining actions: Cast Cryoclasm targeting Forest");
        assertPermanent(Zone.BATTLEFIELD, player2, forest, 1);
    }
}
