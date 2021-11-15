package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class TriggeredAbility extends Ability {


    protected Effects<OneShotEffect> effects = new Effects<>();

    protected TriggeredAbility() {
        super();
    }

    public abstract boolean checkTrigger(Event event, MagicObject parentObject);

}
