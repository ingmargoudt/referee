package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.Objects;

public class EntersTheBattlefieldAbility extends TriggeredAbility {


    public EntersTheBattlefieldAbility(OneShotEffect effect) {
        super();
        effects.addEffect(effect);
    }

    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {
        return event instanceof EnterTheBattlefieldEvent && Objects.equals(event.getSource(), parentObject);
    }


    @Override
    public void resolve(MagicObject source, Game game) {
        effects.apply(source, game);
    }

    @Override
    public String getRule() {
        String effect = effects.toString();
        effect = effect.substring(0,1).toLowerCase() + effect.substring(1);
        return "When {this} enters the battlefield, " + effect;
    }
}
