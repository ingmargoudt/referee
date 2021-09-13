package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Stackable;

public abstract class TriggeredAbility extends Ability implements Stackable {

    public TriggeredAbility(MagicObject source){
        super(source);
    }

    public abstract boolean checkTrigger(Event event);

}
