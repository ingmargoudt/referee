package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Counterable;
import io.github.ingmargoudt.referee.game.targets.Target;

import java.util.stream.Collectors;

public class CounterEffect extends OneShotEffect implements TargetEffect {

    public CounterEffect(Target target) {
        targets.add(target);
    }

    @Override
    public void apply(MagicObject object, Game game) {
        targets.forEach(target ->
                target.resolve(game)
                        .filter(Counterable.class::isInstance)
                        .map(Counterable.class::cast)
                        .ifPresent(theTarget -> theTarget.counter(game)));

    }

    @Override
    public String getRule() {
        return "Counter target " + targets.stream().map(Target::getRule).collect(Collectors.joining());
    }
}
