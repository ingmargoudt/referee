package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;

public enum Phase {
    BEGINNING_PHASE{
        public void run(Game game){
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

        }
    };

    abstract void run(Game game);

    void startMessage(Game game){

        EventBus.report(game.getActivePlayer().getName() + " starts " + this);
        game.setPriority(game.activePlayer);
    }

    void mainPhase(Game game){
        do{
            game.getStack().checkIfAllPlayersPassed();
            game.getPlayer(game.playerWithPriority).doAction();
        }
        while(!game.getStack().isEmpty() || (game.getStack().isEmpty() && !game.getStack().allPlayersPassed()));
    }

}
