package io.github.ingmargoudt.referee.cards.d;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.cards.s.ShivanDragon;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_Disfigure  extends BaseGame {

    @Test
    void DisfigureKillsGrizzlyBear(){
        Card disfigure = new Disfigure();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player2, bears);
        addCard(Zone.HAND, player1, disfigure);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, disfigure, bears);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertGraveyard(player2, bears);
    }

    @Test
    void DisfigureDoesNotKillShivanDragon(){
        Card disfigure = new Disfigure();
        Card shivanDragon = new ShivanDragon();
        addCard(Zone.BATTLEFIELD, player2, shivanDragon);
        addCard(Zone.HAND, player1, disfigure);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, disfigure, shivanDragon);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player2, shivanDragon, 3);
        assertPermanentToughness(Zone.BATTLEFIELD, player2, shivanDragon, 3);
    }

    @Test
    void targetedEffectRetainsTarget(){
        Card disfigure = new Disfigure();
        Card shivanDragon = new ShivanDragon();
        Card anotherShivanDragon = new ShivanDragon();
        addCard(Zone.BATTLEFIELD, player2, shivanDragon);
        addCard(Zone.BATTLEFIELD, player2, anotherShivanDragon);
        addCard(Zone.HAND, player1, disfigure);
        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1, disfigure, shivanDragon);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player2, shivanDragon, 3);
        assertPermanentToughness(Zone.BATTLEFIELD, player2, shivanDragon, 3);
        assertPermanentPower(Zone.BATTLEFIELD, player2, anotherShivanDragon, 5);
        assertPermanentToughness(Zone.BATTLEFIELD, player2, anotherShivanDragon, 5);
    }
}
