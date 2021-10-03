package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Target {


     boolean isValid(MagicObject source, Game game);
     List<Targetable> validTargets();

     Optional<Targetable> resolve(Game game);
     void choose(Stackable source, Game game, OneShotEffect effect);

}
