package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Stack {

    private final LinkedList<Stackable> stackEntries = new LinkedList<>();

    private final Game game;
    private final List<UUID> passed = new ArrayList<>();


    public Stack(Game game) {
        this.game = game;
    }

    public void putOnStack(Stackable stackable) {
        passed.clear();
        stackEntries.addFirst(stackable);
        EventBus.report(stackable.getName() + " is put on the stack");
        if (stackable.hasTargets()) {
            stackable.getEffects().chooseTargets(stackable, game);
        }
        game.setPriority(stackable.getController());
    }

    public void resolve(Game game) {
        Stackable topOfStack = stackEntries.peekFirst();
        Player controller = topOfStack.getController();
        EventBus.report(controller.getName() + " " + topOfStack.getName() + " resolves");
        Stackable stackable = stackEntries.pop();
        stackable.resolve(game);
        game.setPriority(game.getActivePlayer());
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

    public void pass(Game game, Player playerWithPriority) {
        passed.add(playerWithPriority.getId());
        if (stackEntries.isEmpty()) {
            EventBus.report(playerWithPriority.getName() + " passes on empty stack");
        } else {

            EventBus.report(playerWithPriority.getName() + " passes on " + stackEntries.peekFirst().getName());
        }
    }

    public void checkIfAllPlayersPassed() {
        if (allPlayersPassed()) {
            resolve(game);
        }
    }

    public List<Targetable> show() {
        return stackEntries.stream().map(Targetable.class::cast).collect(Collectors.toList());
    }

    public void counter(Spell spell) {
        game.moveToGraveyard(spell.getCard());
        stackEntries.remove(spell);
    }
}
