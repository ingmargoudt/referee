package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.targets.Target;

public class DrainTargetEffect extends OneShotEffect implements TargetEffect {
    int amount;

    public DrainTargetEffect(int amount, Target target) {
        this.amount = amount;
        this.targets.add(target);
    }

    @Override
    public void apply(MagicObject object, Game game) {
        game.getPlayer(object.getController())
                .ifPresent(player ->
                        targets.get(0).resolve(game)
                                .filter(theTarget -> theTarget instanceof Damageable)
                                .map(Damageable.class::cast)
                                .ifPresent(theTarget -> {
                                    theTarget.damage(player, object, amount);
                                    player.gainLife(game, 2, object);
                                }));
    }
}
