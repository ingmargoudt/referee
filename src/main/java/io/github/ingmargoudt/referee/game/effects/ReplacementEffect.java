package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;

public interface ReplacementEffect {

    boolean checkEvent(Event event, MagicObject source, MagicObject parentObject);

    void apply(MagicObject source, Game game);
}
