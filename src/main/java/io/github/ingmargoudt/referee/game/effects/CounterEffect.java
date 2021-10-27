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
        targets.forEach(target ->
                target.resolve(game)
                        .filter(targetable -> targetable instanceof Counterable)
                        .map(Counterable.class::cast)
                        .ifPresent(theTarget -> theTarget.counter(game)));

    }
}
