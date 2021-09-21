package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;

public abstract class ReplacementEffect {

    public abstract boolean checkEvent(Event event, MagicObject source, MagicObject parentObject);

    public abstract void apply(MagicObject source, Game game);
}
