package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GloriousAnthem;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.Zone;
import org.junit.jupiter.api.Test;

public class Test_SimpleCast extends BaseGame {

    private final Card bears = new GrizzlyBears();
    private final Card gloriousAnthem = new GloriousAnthem();

    @Test
    public void simple_cast_and_resolve() {
        addCard(Zone.HAND, player1, bears, 1);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 1);
    }

    @Test
    public void creatureHasPower() {
        addCard(Zone.BATTLEFIELD, player1, bears, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    public void creatureHasToughness() {
        addCard(Zone.BATTLEFIELD, player1, bears, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 2);
    }


    @Test
    public void gloriousAnthemBoosts() {
        addCard(Zone.BATTLEFIELD, player1, bears, 1);
        addCard(Zone.BATTLEFIELD, player1, gloriousAnthem, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 3);
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 3);
    }



}
