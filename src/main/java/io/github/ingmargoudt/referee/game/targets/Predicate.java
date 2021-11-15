package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public interface Predicate {

    boolean evaluate(Targetable target, Game game, MagicObject source);

    String getRule();
}
