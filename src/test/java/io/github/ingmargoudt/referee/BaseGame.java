package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.framework.ConsoleListener;
import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Library;
import io.github.ingmargoudt.referee.players.Player;

public class BaseGame {

    protected Player player1;
    protected Player player2;
    protected Game game;

    public BaseGame(){
        game = new Game();
        player1 = new Player("Player 1", game, new Library());
        player2 = new Player("Player 2", game, new Library());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());
    }
}
