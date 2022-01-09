package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_ShivanDragon extends BaseGame {

    @Test
    void boostOnce(){
        Card shivanDragon = new ShivanDragon();
        addCard(Zone.BATTLEFIELD, player1, shivanDragon);
        activateAbility(1, Phase.PRECOMBAT_MAINPHASE, player1, shivanDragon);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, shivanDragon, 6);
    }
}
