package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;

import java.util.LinkedList;

public class Stack {

    private LinkedList<Stackable> stackEntries = new LinkedList<>();

   public void putOnStack(Stackable stackable){
       stackEntries.addFirst(stackable);
   }

   public void resolve(){
       EventBus.report(stackEntries.peekFirst() + " resolves");
       stackEntries.pop().resolve();
   }
}
