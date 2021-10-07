package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.objects.MagicObject;
import lombok.Getter;

@Getter
public abstract class Event {

    MagicObject source;

    protected Event(MagicObject source) {
        this.source = source;
    }
}
