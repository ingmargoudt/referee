package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Counterable;
import io.github.ingmargoudt.referee.game.targets.Target;

public class CounterEffect extends OneShotEffect implements TargetEffect {

    public CounterEffect(Target target) {
        targets.add(target);
    }

    @Override
    public void apply(MagicObject object, Game game) {
        targets.get(0).resolve(game).ifPresent(theTarget -> {
            if (theTarget instanceof Counterable) {
                ((Counterable) theTarget).counter(game);
            }
        });

    }

    @Override
    public boolean hasValidTargets(Game game) {
        return targets.stream().noneMatch(target -> target.validTargets(game).isEmpty());
    }
}
