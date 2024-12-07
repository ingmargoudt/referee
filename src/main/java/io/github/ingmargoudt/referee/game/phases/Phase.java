package io.github.ingmargoudt.referee.game.phases;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;

import java.lang.invoke.MethodHandles;

public class Phase {

    static void startMessage(Game game) {

        EventBus.report(game.getActivePlayer().getName() + " starts " + MethodHandles.lookup().lookupClass().toString());
        game.setPriority(game.getActivePlayer());

    }

    /*
  500.2. A phase or step in which players receive priority ends when the stack is empty and all players
pass in succession. Simply having the stack become empty doesn’t cause such a phase or step to
end; all players have to pass in succession with the stack empty. Because of this, each player gets a
chance to add new things to the stack before that phase or step ends.
   */
    protected static void handlePriority(Game game) {

        //game.setPriority(game.getActivePlayer());
        do {
            game.getStack().checkIfAllPlayersPassed();
            game.getPlayerWithPriority().doAction();
        }
        while (!game.getStack().isEmpty() || (game.getStack().isEmpty() && !game.getStack().allPlayersPassed()));
        game.getStack().reset();
    }
}
