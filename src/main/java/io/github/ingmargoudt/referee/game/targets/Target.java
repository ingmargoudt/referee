package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;

import java.util.List;
import java.util.Optional;

public abstract class Target {


    public abstract List<Targetable> validTargets(Game game);

    public abstract Optional<Targetable> resolve(Game game);

    public abstract void choose(Stackable source, Game game, OneShotEffect effect);

}
