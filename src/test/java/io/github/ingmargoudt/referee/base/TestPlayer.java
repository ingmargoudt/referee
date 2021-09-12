package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.zones.Hand;
import io.github.ingmargoudt.referee.game.zones.Library;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.*;

public class TestPlayer extends Player {


    private List<TestPlayerAction> actions = new ArrayList<>();



    public TestPlayer(String name, Game game, Library library) {
        super(name, game, library);
    }

    public void addAction(TestPlayerAction playerAction){
        actions.add(playerAction);
    }

    public Hand getHand(){
        return hand;
    }

    @Override
    public void mulligan() {

    }

    @Override
    public void shuffle(){

    }

    public boolean hasRemainingActions(){
        return !actions.isEmpty();
    }



    @Override
    public void doAction() {
        Iterator<TestPlayerAction> playerActionIterator = actions.listIterator();
        while(playerActionIterator.hasNext()){
            TestPlayerAction action = playerActionIterator.next();
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
