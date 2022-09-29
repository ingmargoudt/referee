package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_SealOfDoom extends BaseGame {

    @Test
    void destroyNonBlack(){
        Card sealOfDown = new SealOfDoom();
        Card grizzlyBears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, sealOfDown);
        addCard(Zone.BATTLEFIELD, player2, grizzlyBears);
        activateAbility(1, Phase.PRECOMBAT_MAINPHASE, player1, sealOfDown, grizzlyBears);
        stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
        start();
        assertGraveyard(player2, grizzlyBears);
        assertGraveyard(player1, sealOfDown);

    }

}
