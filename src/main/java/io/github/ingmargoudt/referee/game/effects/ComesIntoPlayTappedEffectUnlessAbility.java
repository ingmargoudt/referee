package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Permanent;
import io.github.ingmargoudt.referee.game.cost.Cost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ComesIntoPlayTappedEffectUnlessAbility implements ReplacementEffect {

    List<Cost> costs = new ArrayList<>();

    @Override
    public boolean checkEvent(Event event, MagicObject source, MagicObject parentObject) {
        return event == Event.ENTERS_THE_BATTLEFIELD && Objects.equals(source, parentObject);

    }

    public ComesIntoPlayTappedEffectUnlessAbility(Cost cost) {
        costs.add(cost);

    }

    @Override
    public void apply(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> {
            if (player.choosesOption(Arrays.asList("Yes", "No")).equals("Yes")) {
                costs.forEach(cost -> cost.pay(source, game));
            } else {
                if (source instanceof Permanent) {
                    ((Permanent) source).tap();
                }
            }
        });
    }
}
