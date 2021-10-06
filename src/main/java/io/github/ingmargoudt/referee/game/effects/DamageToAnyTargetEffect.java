package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.targets.TargetAny;

public class DamageToAnyTargetEffect extends OneShotEffect implements TargetEffect {

    private int amount;

    public DamageToAnyTargetEffect(int amount) {
        this.amount = amount;
        targets.add(new TargetAny());
    }

    @Override
    public void apply(MagicObject object, Game game) {
        targets.get(0).resolve(game).ifPresent(theTarget -> {
            if(theTarget instanceof Damageable){
                EventBus.report(object.getName() + " deals "+amount + " damage to "+theTarget.getName());
                ((Damageable)theTarget).damage(amount);
            }
        });

    }

    @Override
    public boolean hasValidTargets(Game game) {
        return targets.stream().noneMatch(target -> target.validTargets(game).isEmpty());
    }

}
