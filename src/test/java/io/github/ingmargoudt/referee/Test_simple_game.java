package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import org.junit.jupiter.api.Test;

public class Test_simple_game extends BaseGame {

    @Test
    public void simplegame(){
        stopAt(3, Phase.PRECOMBAT_MAINPHASE);
        start();
    }
}
