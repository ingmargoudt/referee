package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public abstract class TriggeredAbility extends Ability {


    protected Effects<OneShotEffect> effects = new Effects<>(OneShotEffect.class);

    protected TriggeredAbility() {
        super();
    }

    public abstract boolean checkTrigger(Event event, MagicObject parentObject);

}
