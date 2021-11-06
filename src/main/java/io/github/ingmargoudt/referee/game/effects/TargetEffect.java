package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Stackable;

public interface TargetEffect {

    boolean hasValidTargets(Game game, MagicObject source);

    void choose(Stackable stackable, Game game);
}
