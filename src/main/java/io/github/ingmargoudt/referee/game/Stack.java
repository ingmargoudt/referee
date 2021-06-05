package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.players.Player;

import java.util.LinkedList;

public class Stack {

    private LinkedList<Stackable> stackEntries = new LinkedList<>();

   public void putOnStack(Stackable stackable){
       stackEntries.addFirst(stackable);
   }

   public void resolve(Game game){
       Stackable topOfStack = stackEntries.peekFirst();
       Player controller = game.getPlayer(topOfStack.getController());
       EventBus.report(controller.getName() + "'s" +stackEntries.peekFirst() + " resolves");
       stackEntries.pop().resolve();
   }

   public boolean isEmpty(){
       return stackEntries.isEmpty();
   }
}
