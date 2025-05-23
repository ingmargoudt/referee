package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.a.AngelsMercy;
import io.github.ingmargoudt.referee.cards.c.CathedralSanctifier;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.l.LightningBolt;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_SimpleCast extends BaseGame {

    private final Card bears = new GrizzlyBears();
    private final Card angelsMercy = new AngelsMercy();
    private final Card cathedralSanctifier = new CathedralSanctifier();

    @Test
    void simple_cast_and_resolve() {
        addCard(Zone.HAND, player1, bears);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 1);
    }


    @Test
    void simple_cast_and_resolve_two_permanents() {
        Card otherbear = new GrizzlyBears();
        addCard(Zone.HAND, player1, bears);
        addCard(Zone.HAND, player1, otherbear);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, otherbear);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    void simple_cast_and_resolve_trigger() {
        addCard(Zone.HAND, player1, cathedralSanctifier);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, cathedralSanctifier);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanent(Zone.BATTLEFIELD, player1, cathedralSanctifier, 1);
        assertLife(player1, 23);
    }

    @Test
    void simple_cast_instant_and_resolve() {
        addCard(Zone.HAND, player1, angelsMercy);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, angelsMercy);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 27);

    }


    @Test
    void creatureHasPower() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, bears);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    void creatureHasToughness() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, bears);

        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentToughness(Zone.BATTLEFIELD, player1, bears, 2);
    }

    @Test
    void castSpellWithSingleTarget() {
        Card bolt = new LightningBolt();
        addCard(Zone.HAND, player1, bolt);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, player2);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player2, 17);
    }

    @Test
    void castSpellWithSingleTargetCreature() {
        Card bolt = new LightningBolt();
        Card bears = new GrizzlyBears();
        addCard(Zone.HAND, player1, bolt);
        addCard(Zone.BATTLEFIELD, player2, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player2, 20);
        assertGraveyard(player2, bears);
    }


    @Test
    void castSpellWithSingleTargetCreature_counteruponresolution() {
        Card bolt = new LightningBolt();
        Card bolt2 = new LightningBolt();
        Card bears = new GrizzlyBears();
        addCard(Zone.HAND, player1, bolt);
        addCard(Zone.HAND, player2, bolt2);
        addCard(Zone.BATTLEFIELD, player2, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bolt, bears);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player2, bolt2, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player2, 20);
        assertGraveyard(player2, bears);
    }

    @Test
    void change_active_player_on_next_turn() {
        disablePlayerActionLogging();
        stopAt(2, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertActivePlayer(player2);
    }


}
