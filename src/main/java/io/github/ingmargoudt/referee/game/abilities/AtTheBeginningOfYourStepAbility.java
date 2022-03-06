package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.AtTheBeginningOfStepEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.Objects;

public class AtTheBeginningOfYourStepAbility extends TriggeredAbility {

    private Step theStep;

    public AtTheBeginningOfYourStepAbility(Step step, OneShotEffect effect) {
        super();
        effects.addEffect(effect);
        theStep = step;
    }

    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {
        return event instanceof AtTheBeginningOfStepEvent && ((AtTheBeginningOfStepEvent) event).getController().equals(parentObject.getController() ) && theStep == ((AtTheBeginningOfStepEvent) event).getStep();
    }


    @Override
    public void resolve(MagicObject source, Game game) {
        effects.apply(source, game);
    }

    @Override
    public String getRule() {
        String effect = effects.getRule();
        effect = effect.substring(0,1).toLowerCase() + effect.substring(1);
        return "At the beginnen of your " + effect;
    }
}
