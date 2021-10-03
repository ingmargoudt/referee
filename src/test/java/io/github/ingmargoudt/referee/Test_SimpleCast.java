package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.a.AngelsMercy;
import io.github.ingmargoudt.referee.cards.c.CathedralSanctifier;
import io.github.ingmargoudt.referee.cards.g.GloriousAnthem;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.l.LightningBolt;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_SimpleCast extends BaseGame {

    private final Card bears = new GrizzlyBears();
    private final Card gloriousAnthem = new GloriousAnthem();
    private final Card angelsMercy = new AngelsMercy();
    private final Card cathedralSanctifier = new CathedralSanctifier();

    @Test
    void simple_cast_and_resolve() {
        addCard(Zone.HAND, player1, bears, 1);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 1);
    }


    @Test
    void simple_cast_and_resolve_two_permanents() {
        Card otherbear = new GrizzlyBears();
        addCard(Zone.HAND, player1, bears, 1);
        addCard(Zone.HAND, player1, otherbear, 1);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, otherbear);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    void simple_cast_and_resolve_trigger() {
        addCard(Zone.HAND, player1, cathedralSanctifier, 1);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, cathedralSanctifier);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, cathedralSanctifier, 1);
        assertLife(player1, 23);
    }

    @Test
    void simple_cast_instant_and_resolve() {
        addCard(Zone.HAND, player1, angelsMercy, 1);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, angelsMercy);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 27);

    }


    @Test
    void creatureHasPower() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, bears, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    void creatureHasToughness() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, bears, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    void castSpellWithSingleTarget(){
        Card bolt = new LightningBolt();
        addCard(Zone.HAND, player1, bolt);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1,  bolt, player2);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player2, 17);
    }

    @Test
    void gloriousAnthemBoosts() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, bears, 1);
        addCard(Zone.BATTLEFIELD, player1, gloriousAnthem, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 3);
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 3);
    }

    @Test
    void gloriousAnthemNotBoostsOtherPlayersCreature() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player2, bears, 1);
        addCard(Zone.BATTLEFIELD, player1, gloriousAnthem, 1);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player2, bears, 2);
        assertPermanentToughness(Zone.BATTLEFIELD, player2, bears, 2);
    }


}
