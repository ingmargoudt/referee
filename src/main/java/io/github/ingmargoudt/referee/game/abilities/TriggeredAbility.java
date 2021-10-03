package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class TriggeredAbility extends Ability {


    @Getter
    private List<OneShotEffect> effects = new ArrayList<>();

    protected TriggeredAbility(){
        super();
    }

    public abstract boolean checkTrigger(Event event, MagicObject source, MagicObject parentObject);

}
