package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class TriggeredAbility extends Ability {


    public List<OneShotEffect> effects = new ArrayList<>();

    public TriggeredAbility(){
        super();
    }

    public abstract boolean checkTrigger(Event event, MagicObject source, MagicObject parentObject);

}
