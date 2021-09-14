package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Stackable;

public abstract class TriggeredAbility extends Ability {

    public TriggeredAbility(){
        super();
    }

    public abstract boolean checkTrigger(Event event);

}
