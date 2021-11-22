package io.github.ingmargoudt.referee.cards.a;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.ReplacementEffect;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.events.GainLifeEvent;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class AngelOfVitality extends Card {
    public AngelOfVitality() {
        super("Angel of Vitality");


        replacementEffects.add(new AngelOfVitalityReplacementEffect());
    }


}

class AngelOfVitalityReplacementEffect implements ReplacementEffect {
    public void repondToEvent(Game game, Event event, MagicObject parentObject) {
        if (event instanceof GainLifeEvent && event.getSource().isControlledBy(parentObject.getController())) {
            ((GainLifeEvent) event).setAmount(((GainLifeEvent) event).getAmount() + 1);
        }
    }

    @Override
    public String getRule() {
        return "If you would gain life, you gain that much life plus 1 instead";
    }


}