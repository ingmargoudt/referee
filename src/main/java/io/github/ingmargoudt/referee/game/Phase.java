package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.events.AtTheBeginningOfStepEvent;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.DurationType;

import java.util.List;
import java.util.stream.Collectors;

public enum Phase {
    BEGINNING_PHASE {
        public void run(Game game) {
            startMessage(game);
            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.UPKEEP, game.getActivePlayer()));
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
            startMessage(game);
            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.BEGINNING_OF_COMBAT, game.getActivePlayer()));
            handlePriority(game);
            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.DECLARE_ATTACKERS, game.getActivePlayer()));
            game.getActivePlayer().doAction();
            handlePriority(game);
            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.DECLARE_BLOCKERS, game.getActivePlayer()));
            game.getNonActivePlayer().doAction();
            handlePriority(game);

            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.COMBAT_DAMAGE_STEP, game.getActivePlayer()));
            List<Permanent> attackers = game.getBattlefield().getAll().stream().filter(p->p.isControlledBy(game.getActivePlayer()) && p.isDeclaredAsAttacker()).collect(Collectors.toList());
            for (Permanent attacker : attackers) {
                if (attacker.getBlockers().isEmpty()) {
                    EventBus.report(attacker.getName() + " is not blocked");
                    game.getNonActivePlayer().damage(game, attacker, attacker.getPower());
                    continue;
                }
                int damageGiven = 0;
                for (Permanent blocker : attacker.getBlockers()) {
                    attacker.damage(game, blocker, blocker.getPower());
                    if (damageGiven < attacker.getPower()) {
                        blocker.damage(game, attacker, attacker.getPower() - damageGiven);
                        damageGiven += attacker.getPower();
                    }
                }
            }
            game.raiseEvent(new AtTheBeginningOfStepEvent(Step.END_OF_COMBAT_STEP, game.getActivePlayer()));
            game.getBattlefield().getAll().forEach(p-> {
                p.setDeclaredAsAttacker(false);
                p.getBlockers().clear();
            });
            handlePriority(game);

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

        game.setPriority(game.getActivePlayer());
        do {
            game.getStack().checkIfAllPlayersPassed();
            game.getPlayerWithPriority().doAction();
        }
        while (!game.getStack().isEmpty() || (game.getStack().isEmpty() && !game.getStack().allPlayersPassed()));
        game.getStack().reset();
    }

}
