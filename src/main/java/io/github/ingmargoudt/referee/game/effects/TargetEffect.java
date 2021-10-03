package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Stackable;

public interface TargetEffect {

    boolean hasValidTargets(Game game);
    void choose(Stackable stackable, Game game);
}
