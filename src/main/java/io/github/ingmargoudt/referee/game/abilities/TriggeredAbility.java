package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public abstract class TriggeredAbility extends StackableAbility {



    protected TriggeredAbility() {
        super();
    }

    public abstract boolean checkTrigger(Event event, MagicObject parentObject);

}
