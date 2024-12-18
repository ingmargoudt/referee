package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;

import java.util.List;
import java.util.Optional;

public abstract class Target implements Ruleable {

    Filter filter = Filter.empty();
    MagicObject source;


    public abstract List<Targetable> validTargets(Game game);

    public abstract Optional<Targetable> resolve(Game game);

    public abstract void choose(Stackable source, Game game, TargetEffect effect);

    public Target from(MagicObject source) {
        this.source = source;
        return this;
    }
}
