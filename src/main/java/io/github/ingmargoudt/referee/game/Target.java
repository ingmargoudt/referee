package io.github.ingmargoudt.referee.game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Target {


     boolean isValid(MagicObject source, Game game);
     List<Targetable> validTargets();

     Optional<Targetable> resolve(Game game);
     void choose(UUID source, Game game);

}
