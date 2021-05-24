package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.game.Game;
import org.junit.jupiter.api.Test;

public class Test_simple_game extends BaseGame{

    @Test
    public void simplegame(){
        game.gameloop();
    }
}
