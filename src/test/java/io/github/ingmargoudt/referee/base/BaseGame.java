package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.framework.*;
import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BaseGame {

    protected TestPlayer player1;
    protected TestPlayer player2;
    private Game game;
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
        player1 = new TestPlayer("Player 1", game, createLibraries());
        player2 = new TestPlayer("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());
        InputBus.registerListener(inputListener);

    }

    public void castSpell(int turn, Phase phase, TestPlayer player,Card card){
        player.addAction(new CastSpellAction(turn, phase, card));
    }

    public void stopAt(int turn, Phase phase){
        game.stopAt(turn, phase);
    }

    public void start(){
        game.start();
    }

    public void addCard(Zone zone, TestPlayer player, Card card, int i) {
        if(zone == Zone.HAND){
            card.setController(player.getId());
            player.getHand().addCard(card);
        }
    }

    public void addCommand(UUID uuid, String command){
        inputListener.addInput(uuid , command);
    }
}
