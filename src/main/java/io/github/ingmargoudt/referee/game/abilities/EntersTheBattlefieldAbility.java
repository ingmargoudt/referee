package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import java.util.*;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

public class EntersTheBattlefieldAbility extends TriggeredAbility {

    public List<OneShotEffect> effects = new ArrayList<>();

    public EntersTheBattlefieldAbility(OneShotEffect effect){
        super();
        effects.add(effect);
    }

    @Override
    public boolean checkTrigger(Event event) {
        return event == Event.ENTERS_THE_BATTLEFIELD;
    }


    @Override
    public void resolve(MagicObject source, Game game) {
        effects.forEach(effect -> effect.apply(source, game));
    }
}
