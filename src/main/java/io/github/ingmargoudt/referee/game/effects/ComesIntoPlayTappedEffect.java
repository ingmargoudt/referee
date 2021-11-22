package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;

import java.util.Objects;

public class ComesIntoPlayTappedEffect implements ReplacementEffect {
    @Override
    public void repondToEvent(Game game, Event event, MagicObject parentObject) {
        MagicObject source = event.getSource();
        if (event instanceof EnterTheBattlefieldEvent && Objects.equals(source, parentObject)) {
            if (source instanceof Permanent) {
                ((Permanent) source).tap();
            }
        }
    }

    @Override
    public String getRule() {
        return "{this} comes into play tapped";
    }
}
