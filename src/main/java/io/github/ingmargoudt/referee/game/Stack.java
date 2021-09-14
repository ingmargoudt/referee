package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Stack {

    private LinkedList<Stackable> stackEntries = new LinkedList<>();

    private Game game;

    public Stack(Game game) {
        this.game = game;
    }


    public void putOnStack(Stackable stackable) {
        stackEntries.addFirst(stackable);
        EventBus.report(stackable.getName()+ " is put on the stack");
    }

    private List<UUID> passed = new ArrayList<>();

    public void resolve(Game game) {
        Stackable topOfStack = stackEntries.peekFirst();
        game.getPlayer(topOfStack.getController()).ifPresent(controller -> {
            EventBus.report(controller.getName() + " " + stackEntries.peekFirst().getName() + " resolves");
            Stackable stackable = stackEntries.pop();
            stackable.resolve(game);
            game.setPriority(stackable.getController());
        });
        passed.clear();
    }

    public boolean isEmpty() {
        return stackEntries.isEmpty();
    }

    public void reset() {
        stackEntries.clear();
        passed.clear();
    }

    public boolean allPlayersPassed() {
        return passed.size() == 2;
    }

    public void pass(Game game, UUID playerWithPriority) {
        passed.add(playerWithPriority);
        if (stackEntries.isEmpty()) {
            game.getPlayer(playerWithPriority).ifPresent(player -> EventBus.report(player.getName() + " passes on empty stack"));
        } else {

            game.getPlayer(playerWithPriority).ifPresent(player -> EventBus.report(player.getName() + " passes on " + stackEntries.peekFirst().getName()));
        }
    }

    public void checkIfAllPlayersPassed() {
        if (allPlayersPassed()) {
            resolve(game);
        }
    }
}
