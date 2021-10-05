package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.target.TargetAny;

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
                ((Damageable)theTarget).damage(amount);
            }
        });

    }

    @Override
    public boolean hasValidTargets(Game game) {
        return true;
    }

}
