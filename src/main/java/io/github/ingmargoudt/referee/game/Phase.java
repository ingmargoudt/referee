package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.abilities.statics.FirstStrike;
import io.github.ingmargoudt.referee.game.events.AtTheBeginningOfStepEvent;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.phases.CombatPhase;
import io.github.ingmargoudt.referee.game.properties.DurationType;

import java.util.List;
import java.util.stream.Collectors;

public enum Phase {
    BEGINNING_PHASE {
        public void run(Game game) {
            startMessage(game);
            game.getActivePlayer().executeUntapStep();
            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.UPKEEP, game));
            handlePriority(game);
            game.getActivePlayer().drawCard();
            handlePriority(game);
        }
    },
    PRECOMBAT_MAINPHASE {
        @Override
        void run(Game game) {
            startMessage(game);
            handlePriority(game);
        }
    },
    COMBAT_PHASE {
        @Override
        void run(Game game) {
            CombatPhase.run(game);

        }
    },
    POSTCOMBAT_MAINPHASE {
        @Override
        void run(Game game) {
            startMessage(game);
            handlePriority(game);

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

            EventBus.report(game.getActivePlayer().getName() + " starts " + this);
            game.setPriority(game.getActivePlayer());

    }

    /*
    500.2. A phase or step in which players receive priority ends when the stack is empty and all players
pass in succession. Simply having the stack become empty doesn’t cause such a phase or step to
end; all players have to pass in succession with the stack empty. Because of this, each player gets a
chance to add new things to the stack before that phase or step ends.
     */
    void handlePriority(Game game) {

        //game.setPriority(game.getActivePlayer());
        do {
            game.getStack().checkIfAllPlayersPassed();
            game.getPlayerWithPriority().doAction();
        }
        while (!game.getStack().isEmpty() || (game.getStack().isEmpty() && !game.getStack().allPlayersPassed()));
        game.getStack().reset();
    }

}
