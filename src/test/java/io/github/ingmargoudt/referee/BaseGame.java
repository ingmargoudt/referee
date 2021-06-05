package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.framework.*;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Library;
import io.github.ingmargoudt.referee.players.Player;

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
            cards.add(new Card("Mountain"));
        }
        return new Library(cards);
    }


    public BaseGame(){
        inputListener = new TestInputListener();
        game = new Game();
        player1 = new Player("Player 1", game, createLibraries());
        player2 = new Player("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());
        InputBus.registerListener(inputListener);
        addCommand(player1.getId(), "no"); // don't mulligan
        addCommand(player2.getId(), "no");

    }

    public void addCommand(UUID uuid, String command){
        inputListener.addInput(uuid , command);
    }
}