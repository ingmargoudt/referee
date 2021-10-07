package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class EnterTheBattlefieldEvent extends Event {

    public EnterTheBattlefieldEvent(MagicObject source) {
        super(source);
    }

}
