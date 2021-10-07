package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;

import java.util.List;
import java.util.Optional;

public interface Target {


    List<Targetable> validTargets(Game game);

    Optional<Targetable> resolve(Game game);

    void choose(Stackable source, Game game, OneShotEffect effect);

}
