package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

import java.util.Objects;

public class EntersTheBattlefieldAbility extends TriggeredAbility {


    public EntersTheBattlefieldAbility(OneShotEffect effect){
        super();
        getEffects().add(effect);
    }

    @Override
    public boolean checkTrigger(Event event, MagicObject sourceOfTrigger, MagicObject parentObject) {
        return event == Event.ENTERS_THE_BATTLEFIELD && Objects.equals(sourceOfTrigger, parentObject) ;
    }


    @Override
    public void resolve(MagicObject source, Game game) {
        getEffects().forEach(effect -> effect.apply(source, game));
    }
}
