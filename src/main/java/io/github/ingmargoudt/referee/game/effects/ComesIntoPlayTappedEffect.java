package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;

import java.util.Objects;

public class ComesIntoPlayTappedEffect implements ReplacementEffect {
    @Override
    public boolean checkEvent(Event event, MagicObject parentObject) {
        return event instanceof EnterTheBattlefieldEvent && Objects.equals(event.getSource(), parentObject);
    }

    @Override
    public void apply(MagicObject source, Game game) {
        if (source instanceof Permanent) {
            ((Permanent) source).tap();
        }
    }
}
