package io.github.ingmargoudt.referee.lands;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.f.Forest;
import io.github.ingmargoudt.referee.cards.f.ForsakenSanctuary;
import io.github.ingmargoudt.referee.cards.i.Island;
import io.github.ingmargoudt.referee.cards.m.Mountain;
import io.github.ingmargoudt.referee.cards.p.Plains;
import io.github.ingmargoudt.referee.cards.s.Swamp;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.abilities.mana.*;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_lands_have_mana_abilities extends BaseGame {

    private static final Card mountain = new Mountain();
    private static final Card plains = new Plains();
    private static final Card swamp = new Swamp();
    private static final Card island = new Island();
    private static final Card forest = new Forest();

    @Test
    void mountain() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, mountain, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentHasAbility(player1, mountain, AddRedManaAbility.class);
    }

    @Test
    void plains() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, plains, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentHasAbility(player1, plains, AddWhiteManaAbility.class);
    }

    @Test
    void swamp() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, swamp, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentHasAbility(player1, swamp, AddBlackManaAbility.class);
    }

    @Test
    void island() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, island, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentHasAbility(player1, swamp, AddBlueManaAbility.class);
    }

    @Test
    void forest() {
        disablePlayerActionLogging();
        addCard(Zone.BATTLEFIELD, player1, forest, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentHasAbility(player1, swamp, AddGreenManaAbility.class);
    }

    @Test
    void forsakenSanctuary(){
        Card forsakenSanctuary = new ForsakenSanctuary();
        addCard(Zone.BATTLEFIELD, player1, forsakenSanctuary, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        activateAbility(1, Phase.PRECOMBAT_MAINPHASE, player1, forsakenSanctuary);
        start();
        assertTapped(player1, forsakenSanctuary);
    }
}
