package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.DrawCardEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class DrawsACardAbility extends TriggeredAbility{

    private OneShotEffect oneshotEffect;

    public DrawsACardAbility(OneShotEffect oneShotEffect){
        this.oneshotEffect = oneShotEffect;
    }

    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {

        if(event instanceof DrawCardEvent) {
            oneshotEffect.setReflectedSource(((DrawCardEvent) event).getPlayer());
            return true;
        }
        return false;
    }

    @Override
    public void resolve(MagicObject source, Game game) {
       oneshotEffect.apply(source, game);
    }

    @Override
    public String getRule() {
        return "";
    }
}
