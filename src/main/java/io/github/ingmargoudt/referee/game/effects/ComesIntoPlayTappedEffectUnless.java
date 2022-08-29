package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Ruleable;

import java.util.Arrays;
import java.util.Objects;

public class ComesIntoPlayTappedEffectUnless implements ReplacementEffect, Ruleable {

    Costs costs = new Costs();

    public ComesIntoPlayTappedEffectUnless(Cost cost) {
        costs.addCost(cost);

    }

    @Override
    public void respondToEvent(Game game, Event event, MagicObject parentObject) {
        MagicObject source = event.getSource();
        if (event instanceof EnterTheBattlefieldEvent && Objects.equals(event.getSource(), parentObject)) {
                if (costs.canPay(source, game) && source.getController().choosesOption(Arrays.asList("Yes", "No")).equals("Yes")) {
                    costs.payAll(source, game);
                } else {
                    if (source instanceof Permanent) {
                        ((Permanent) source).tap();
                    }
                }

        }
    }

    @Override
    public String getRule() {
        return "As {this} enters the battlefield, you may "+costs.toString() +
                ". If you don't, it enters the battlefield tapped.";
    }
}
