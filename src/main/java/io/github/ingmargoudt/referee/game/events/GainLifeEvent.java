package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class GainLifeEvent extends Event {

    public GainLifeEvent(MagicObject source) {
        super(source);
    }
}
