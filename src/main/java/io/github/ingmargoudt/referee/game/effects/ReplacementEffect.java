package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public interface ReplacementEffect {

    void repondToEvent(Game game, Event event, MagicObject parentObject);

    String getRule();

}
