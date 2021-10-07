package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.Objects;

public class EntersTheBattlefieldAbility extends TriggeredAbility {


    public EntersTheBattlefieldAbility(OneShotEffect effect){
        super();
        getEffects().add(effect);
    }

    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {
        return event instanceof EnterTheBattlefieldEvent && Objects.equals(event.getSource(), parentObject) ;
    }


    @Override
    public void resolve(MagicObject source, Game game) {
        getEffects().forEach(effect -> effect.apply(source, game));
    }
}
