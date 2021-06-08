package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.players.Player;

import java.util.LinkedList;
import java.util.UUID;

import java.util.*;
public class Stack {

    private LinkedList<Stackable> stackEntries = new LinkedList<>();

    private Game game;

    public Stack(Game game){
        this.game = game;
    }



   public void putOnStack(Stackable stackable){
       stackEntries.addFirst(stackable);
   }

   private List<UUID> passed = new ArrayList<>();

   public void resolve(Game game){
       Stackable topOfStack = stackEntries.peekFirst();
       Player controller = game.getPlayer(topOfStack.getController());
       EventBus.report(controller.getName() + " " +stackEntries.peekFirst().getName() + " resolves");
       Stackable stackable = stackEntries.pop();
       stackable.resolve(game);
       game.setPriority(stackable.getController());
       passed.clear();
   }

   public boolean isEmpty(){
       return stackEntries.isEmpty();
   }

   public void reset(){
       stackEntries.clear();
       passed.clear();
   }

    public boolean allPlayersPassed() {
       return passed.size() == 2;
    }

    public void pass(Game game, UUID playerWithPriority) {
       passed.add(playerWithPriority);
        if(stackEntries.isEmpty()){
            EventBus.report(game.getPlayer(playerWithPriority).getName() + " passes on empty stack");
        }
        else{

            EventBus.report(game.getPlayer(playerWithPriority).getName() + " passes on "+stackEntries.peekFirst().getName());
        }
    }

    public void checkIfAllPlayersPassed() {
        if(allPlayersPassed()){
            resolve(game);
        }
    }
}
