package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_BurrowguardMentor extends BaseGame {

    @Test
    void checkPT(){
        Card burrowguardMentor = new BurrowguardMentor();
        Card bears = new GrizzlyBears();
        addCard(Zone.BATTLEFIELD, player1, burrowguardMentor);
        addCard(Zone.BATTLEFIELD, player1, bears);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, burrowguardMentor, 2);
    }

    @Test
    void twoBurrowGuardMentorBoostEachother(){
        Card burrowguardMentor = new BurrowguardMentor();
        Card burrowguardMentor2 = new BurrowguardMentor();
        addCard(Zone.BATTLEFIELD, player1, burrowguardMentor);
        addCard(Zone.BATTLEFIELD, player1, burrowguardMentor2);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentPower(Zone.BATTLEFIELD, player1, burrowguardMentor, 2);
        assertPermanentPower(Zone.BATTLEFIELD, player1, burrowguardMentor2, 2);
    }
}
