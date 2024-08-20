package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.targets.Target;
import io.github.ingmargoudt.referee.players.Player;

import java.util.stream.Collectors;

public class DamageTargetEffect extends OneShotEffect implements TargetEffect {

    private final int amount;

    public DamageTargetEffect(int amount, Target target) {
        this.amount = amount;
        targets.add(target);
    }

    @Override
    public void apply(MagicObject object, Game game) {
        Player player = object.getController();
        targets.get(0).resolve(game)
                .filter(Damageable.class::isInstance)
                .map(Damageable.class::cast)
                .ifPresent(theTarget -> {
                    theTarget.receiveDamage(game, object, amount);
                });

    }

    @Override
    public String getRule() {
        return "{this} deals " + amount + " damage to " + targets.stream().map(Target::getRule).collect(Collectors.joining());
    }
}
