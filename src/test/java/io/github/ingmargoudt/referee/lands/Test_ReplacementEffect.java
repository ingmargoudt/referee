package io.github.ingmargoudt.referee.lands;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.a.AngelOfVitality;
import io.github.ingmargoudt.referee.cards.a.AngelsMercy;
import io.github.ingmargoudt.referee.cards.b.BloodCrypt;
import io.github.ingmargoudt.referee.cards.b.BloodMoon;
import io.github.ingmargoudt.referee.cards.b.BloodSun;
import io.github.ingmargoudt.referee.cards.f.ForsakenSanctuary;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.SubTypes;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

class Test_ReplacementEffect extends BaseGame {

    @Test
    void tap_land_comes_into_play_tapped() {
        Card forsakenSanctuary = new ForsakenSanctuary();
        addCard(Zone.HAND, player1, forsakenSanctuary);
        playLand(player1, 1, Phase.PRECOMBAT_MAINPHASE, forsakenSanctuary);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertTapped(player1, forsakenSanctuary);
    }

    @Test
    void tap_land_comes_into_play_untapped_with_bloodmoon() {
        Card forsakenSanctuary = new ForsakenSanctuary();
        Card bloodmoon = new BloodMoon();
        addCard(Zone.HAND, player1, forsakenSanctuary);
        addCard(Zone.BATTLEFIELD, player1, bloodmoon);
        playLand(player1, 1, Phase.PRECOMBAT_MAINPHASE, forsakenSanctuary);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertUntapped(player1, forsakenSanctuary);
        SubTypes expectedSubTypes = new SubTypes();
        expectedSubTypes.add(SubType.MOUNTAIN);
        assertSubTypes(player1, forsakenSanctuary, expectedSubTypes);
    }

    @Test
    void shock_land_comes_into_play_untapped_with_bloodmoon() {
        Card bloodcrypt = new BloodCrypt();
        Card bloodmoon = new BloodMoon();
        addCard(Zone.HAND, player1, bloodcrypt);
        addCard(Zone.BATTLEFIELD, player1, bloodmoon);
        playLand(player1, 1, Phase.PRECOMBAT_MAINPHASE, bloodcrypt);
        setOption(player1, "Yes");
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertUntapped(player1, bloodcrypt);
    }

    @Test
    void shock_land_comes_into_play_untapped_after_cost() {
        Card bloodcrypt = new BloodCrypt();
        addCard(Zone.HAND, player1, bloodcrypt);
        playLand(player1, 1, Phase.PRECOMBAT_MAINPHASE, bloodcrypt);
        setOption(player1, "Yes");
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertUntapped(player1, bloodcrypt);
        assertLife(player1, 18);
    }

    @Test
    void shock_land_comes_into_play_tapped_after_not_paying_cost() {
        Card bloodcrypt = new BloodCrypt();
        addCard(Zone.HAND, player1, bloodcrypt);
        playLand(player1, 1, Phase.PRECOMBAT_MAINPHASE, bloodcrypt);
        setOption(player1, "No");
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertTapped(player1, bloodcrypt);
    }

    @Test
    void shock_land_comes_into_play_tapped_after_not_possible_paying_cost() {
        Card bloodcrypt = new BloodCrypt();
        addCard(Zone.HAND, player1, bloodcrypt);
        setLife(player1, 1);
        playLand(player1, 1, Phase.PRECOMBAT_MAINPHASE, bloodcrypt);
        //setOption(player1,"No");
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertTapped(player1, bloodcrypt);
    }


    @Test
    void replacement_Effect_gainlife() {
        Card angelsMercy = new AngelsMercy();
        Card angelOfVitality = new AngelOfVitality();
        addCard(Zone.HAND, player1, angelsMercy, 1);
        addCard(Zone.BATTLEFIELD, player1, angelOfVitality);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, angelsMercy);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertLife(player1, 28);

    }

    @Test
    void bloodSun(){
        Card bloodSun = new BloodSun();
        addCard(Zone.HAND, player1, bloodSun);
        Card forsakenSanctuary = new ForsakenSanctuary();
        addCard(Zone.BATTLEFIELD, player1, forsakenSanctuary);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, bloodSun);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertAbilityCount(player1, forsakenSanctuary, 0);
    }
}
