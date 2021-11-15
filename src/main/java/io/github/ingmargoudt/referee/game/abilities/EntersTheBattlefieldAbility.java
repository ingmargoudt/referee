package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;

import java.util.Objects;
import java.util.stream.Collectors;

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
        return "When {this} enters the battlefield, " + effects.toString();
    }
}
