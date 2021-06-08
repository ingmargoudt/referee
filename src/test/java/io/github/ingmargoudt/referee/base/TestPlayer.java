package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Hand;
import io.github.ingmargoudt.referee.game.Library;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.*;

public class TestPlayer extends Player {

    private List<PlayerAction> actions = new ArrayList<>();

    public TestPlayer(String name, Game game, Library library) {
        super(name, game, library);
    }

    public void addAction(PlayerAction playerAction){
        actions.add(playerAction);
    }

    public Hand getHand(){
        return hand;
    }

    @Override
    public void mulligan() {

    }



    @Override
    public void doAction() {
        Iterator<PlayerAction> playerActionIterator = actions.listIterator();
        while(playerActionIterator.hasNext()){
            PlayerAction action = playerActionIterator.next();
            if(action.phase == gameReference.getCurrentPhase() && action.turn == gameReference.getTurnNumber())
            {
                if(action instanceof CastSpellAction){
                    action.execute(this);
                    playerActionIterator.remove();
                    return;
                }
            };
        }
        passPriority();

    }
}
