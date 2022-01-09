package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.properties.DurationType;
import io.github.ingmargoudt.referee.players.Player;

public enum Phase {
    BEGINNING_PHASE {
        public void run(Game game) {
            startMessage(game);
        }
    },
    PRECOMBAT_MAINPHASE {
        @Override
        void run(Game game) {
            startMessage(game);
            mainPhase(game);
        }
    },
    COMBAT_PHASE {
        @Override
        void run(Game game) {
            startMessage(game);

        }
    },
    POSTCOMBAT_MAINPHASE {
        @Override
        void run(Game game) {
            startMessage(game);

        }
    },
    END_PHASE {
        @Override
        void run(Game game) {
            startMessage(game);
            game.getContinuousEffects().values()
                    .forEach(continuousEffectSource ->
                            continuousEffectSource.removeIf(continuousEffect -> continuousEffect.getDuration().getDurationType() == DurationType.UNTIL_END_OF_TURN
                                    && continuousEffect.getDuration().getTurn() == game.getTurnNumber()));

        }
    };

    abstract void run(Game game);

    void startMessage(Game game) {

        game.getActivePlayer().ifPresent(activePlayer -> {
            EventBus.report(activePlayer.getName() + " starts " + this);
            game.setPriority(activePlayer.getId());
        });
    }

    /*
    500.2. A phase or step in which players receive priority ends when the stack is empty and all players
pass in succession. Simply having the stack become empty doesn’t cause such a phase or step to
end; all players have to pass in succession with the stack empty. Because of this, each player gets a
chance to add new things to the stack before that phase or step ends.
     */
    void mainPhase(Game game) {
        do {
            game.getStack().checkIfAllPlayersPassed();
            game.getPlayerWithPriority().ifPresent(Player::doAction);
        }
        while (!game.getStack().isEmpty() || (game.getStack().isEmpty() && !game.getStack().allPlayersPassed()));
        game.getStack().reset();
    }

}
