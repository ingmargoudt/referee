package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Controllable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

@Getter
public abstract class Event implements Controllable {

    MagicObject source;

    protected Event(MagicObject source) {
        this.source = source;
    }

    protected Event(){

    }

    public boolean isControlledBy(Player thePlayer) {
        return source.getController().equals(thePlayer);
    }
}
