package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.targets.Target;

public class DamageTargetEffect extends OneShotEffect implements TargetEffect {

    private int amount;

    public DamageTargetEffect(int amount, Target target) {
        this.amount = amount;
        targets.add(target);
    }

    @Override
    public void apply(MagicObject object, Game game) {
        game.getPlayer(object.getController())
                .ifPresent(player -> targets.get(0).resolve(game)
                        .filter(Damageable.class::isInstance)
                        .map(Damageable.class::cast)
                        .ifPresent(theTarget -> {
                            theTarget.damage(player, object, amount);
                        }));

    }
}
