package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import java.util.*;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

public class EntersTheBattlefieldAbility extends TriggeredAbility {

    public List<OneShotEffect> effects = new ArrayList<>();

    public EntersTheBattlefieldAbility(OneShotEffect effect, MagicObject source){
        super(source);
        effects.add(effect);
    }

    @Override
    public boolean checkTrigger(Event event) {
        return event == Event.ENTERS_THE_BATTLEFIELD;
    }

    @Override
    public String getName() {
        return effects.get(0).getClass().getSimpleName() + " from "+getSource().getName();
    }

    @Override
    public void resolve(Game game) {
        effects.forEach(effect -> effect.apply(getSource(), game));
    }
}
