package io.github.ingmargoudt.referee.game.phases;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.abilities.statics.FirstStrike;
import io.github.ingmargoudt.referee.game.events.AtTheBeginningOfStepEvent;
import io.github.ingmargoudt.referee.game.objects.Permanent;

import java.util.List;
import java.util.stream.Collectors;

public class CombatPhase extends Phase{

    public static void run(Game game) {
        startMessage(game);
        game.raiseEvent(new AtTheBeginningOfStepEvent(Step.BEGINNING_OF_COMBAT, game));
        handlePriority(game);
        game.raiseEvent(new AtTheBeginningOfStepEvent(Step.DECLARE_ATTACKERS, game));
        game.getActivePlayer().doAction();
        handlePriority(game);
        List<Permanent> attackers = game.getBattlefield().getAll().stream().filter(p->p.isControlledBy(game.getActivePlayer()) && p.isDeclaredAsAttacker() ).collect(Collectors.toList());
        if(attackers.isEmpty()){
            EventBus.report("No attackers declares, skipping combat phase");
            return;
        }
        game.raiseEvent(new AtTheBeginningOfStepEvent(Step.DECLARE_BLOCKERS, game));
        game.getNonActivePlayer().doAction();
        handlePriority(game);

        game.raiseEvent(new AtTheBeginningOfStepEvent(Step.COMBAT_DAMAGE_STEP, game));

        // firststrike + double strike
        for (var attacker : attackers) {
            if (attacker.getBlockers().isEmpty() && attacker.hasAbility(FirstStrike.class)) {
                EventBus.report(attacker.getName() + " is not blocked");
                game.getNonActivePlayer().receiveDamage(game, attacker, attacker.getPower());
                continue;
            }
            int damageGiven = 0;
            for (var blocker : attacker.getBlockers()) {
                if(blocker.hasAbility(FirstStrike.class)) {
                    attacker.receiveDamage(game, blocker, blocker.getPower());
                }
                if (attacker.hasAbility(FirstStrike.class) && damageGiven < attacker.getPower()) {
                    blocker.receiveDamage(game, attacker, attacker.getPower() - damageGiven);
                    damageGiven += attacker.getPower();
                }
            }
        }
        handlePriority(game);
        game.getBattlefield().getAll().stream().filter(p->p.isControlledBy(game.getActivePlayer()) && p.isDeclaredAsAttacker()).forEach(attacker -> {
            attacker.getBlockers().removeIf(blocker -> blocker.getReceivedDamage() >= blocker.getToughness());
        });
        // regular + double strike

        attackers = game.getBattlefield().getAll().stream().filter(p->p.isControlledBy(game.getActivePlayer()) && p.isDeclaredAsAttacker()).collect(Collectors.toList());
        for (var attacker : attackers) {
            if (attacker.getBlockers().isEmpty() && !attacker.hasAbility(FirstStrike.class)) {
                EventBus.report(attacker.getName() + " is not blocked");
                game.getNonActivePlayer().receiveDamage(game, attacker, attacker.getPower());
                continue;
            }
            int damageGiven = 0;
            for (var blocker : attacker.getBlockers()) {
                if(!blocker.hasAbility(FirstStrike.class)) {
                    attacker.receiveDamage(game, blocker, blocker.getPower());
                }
                if (damageGiven < attacker.getPower()) {
                    if(!attacker.hasAbility(FirstStrike.class)) {
                        blocker.receiveDamage(game, attacker, attacker.getPower() - damageGiven);
                        damageGiven += attacker.getPower();
                    }
                }
            }
        }
        handlePriority(game);
        game.raiseEvent(new AtTheBeginningOfStepEvent(Step.END_OF_COMBAT_STEP, game));
        game.getBattlefield().getAll().forEach(p-> {
            p.setDeclaredAsAttacker(false);
            p.getBlockers().clear();
        });
        handlePriority(game);

    }
}
