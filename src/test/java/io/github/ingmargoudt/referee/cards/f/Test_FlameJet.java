package io.github.ingmargoudt.referee.cards.f;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_FlameJet extends BaseGame {

    @Test
    void simpleCycling(){
        Card flameJet = new FlameJet();
        addCard(Zone.HAND, player1, flameJet);
        activateAbility(1, Phase.PRECOMBAT_MAINPHASE, player1, flameJet);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
    }
}
