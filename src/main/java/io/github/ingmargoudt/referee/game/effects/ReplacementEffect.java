package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public interface ReplacementEffect {

    boolean checkEvent(Event event, MagicObject parentObject);

    void apply(MagicObject source, Game game);
}
