package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.cost.Costs;

import java.util.Arrays;
import java.util.Objects;

public class ComesIntoPlayTappedEffectUnless implements ReplacementEffect {

    Costs costs = new Costs();

    @Override
    public boolean checkEvent(Event event, MagicObject source, MagicObject parentObject) {
        return event == Event.ENTERS_THE_BATTLEFIELD && Objects.equals(source, parentObject);

    }

    public ComesIntoPlayTappedEffectUnless(Cost cost) {
        costs.addCost(cost);

    }

    @Override
    public void apply(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> {
            if (costs.canPay(source, game) && player.choosesOption(Arrays.asList("Yes", "No")).equals("Yes")) {
                costs.payAll(source,game);
            } else {
                if (source instanceof Permanent) {
                    ((Permanent) source).tap();
                }
            }
        });
    }
}
