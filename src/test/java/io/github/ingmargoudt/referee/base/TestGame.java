package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Battlefield;
import io.github.ingmargoudt.referee.game.Game;

public class TestGame extends Game {

    public Battlefield getBattlefield(){
        return battlefield;
    }
}
