package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.framework.*;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Library;
import io.github.ingmargoudt.referee.players.Player;
import io.github.ingmargoudt.referee.players.TestPlayer;

import java.util.ArrayList;
import java.util.UUID;

public class BaseGame {

    protected Player player1;
    protected Player player2;
    protected Game game;
    private TestInputListener inputListener;

    private Library createLibraries(){
        ArrayList<Card>cards = new ArrayList<>();
        for(int i = 0; i<60;i++){
            cards.add(new Card("Mountain", UUID.randomUUID()));
        }
        return new Library(cards);
    }


    public BaseGame(){
        inputListener = new TestInputListener();
        game = new Game();
        player1 = new TestPlayer("Player 1", game, createLibraries());
        player2 = new TestPlayer("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());
        InputBus.registerListener(inputListener);

    }

    public void addCommand(UUID uuid, String command){
        inputListener.addInput(uuid , command);
    }
}
