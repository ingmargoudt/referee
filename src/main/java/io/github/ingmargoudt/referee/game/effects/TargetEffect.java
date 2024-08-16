package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.targets.Target;

import java.util.List;

public interface TargetEffect {

    boolean hasValidTargets(Game game, MagicObject source);

    void choose(Stackable stackable, Game game);

    List<Target> getTargets();
}
