package io.github.ingmargoudt.referee.game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class Target {


    public abstract boolean isValid(MagicObject source, Game game);
    public abstract List<Targetable> validTargets();

    public abstract Optional<Targetable> resolve(Game game);
    public abstract void choose(UUID source, Game game);

}
