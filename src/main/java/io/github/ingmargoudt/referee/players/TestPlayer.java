package io.github.ingmargoudt.referee.players;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Library;

public class TestPlayer extends Player{
    public TestPlayer(String name, Game game, Library library) {
        super(name, game, library);
    }

    @Override
    public void mulligan() {

    }
}
