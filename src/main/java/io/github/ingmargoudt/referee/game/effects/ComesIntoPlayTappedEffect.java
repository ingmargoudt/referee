package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Permanent;

import java.util.Objects;

public class ComesIntoPlayTappedEffect extends ReplacementEffect {
    @Override
    public boolean checkEvent(Event event, MagicObject source, MagicObject parentObject) {
        return event == Event.ENTERS_THE_BATTLEFIELD && Objects.equals(source, parentObject);
    }

    @Override
    public void apply(MagicObject source, Game game) {
        if (source instanceof Permanent) {
            ((Permanent) source).tap();
        }
    }
}
