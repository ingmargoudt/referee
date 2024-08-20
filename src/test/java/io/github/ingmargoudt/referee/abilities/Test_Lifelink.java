package io.github.ingmargoudt.referee.abilities;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.b.BrightbladeStoat;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.abilities.FirstStrike;
import io.github.ingmargoudt.referee.game.abilities.Lifelink;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_Lifelink extends BaseGame {

    @Test
    void lifelinkGainsLife(){

            Card brightbladeStoat = new BrightbladeStoat();
            addCard(Zone.BATTLEFIELD, player1, brightbladeStoat);
            attack(player1, 1, brightbladeStoat);
            stopAt(1, Phase.POSTCOMBAT_MAINPHASE);
            start();
            assertLife(player1, 22);
        }
    }