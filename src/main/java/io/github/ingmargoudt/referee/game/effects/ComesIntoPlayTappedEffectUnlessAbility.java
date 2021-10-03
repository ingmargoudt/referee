package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.framework.Question;
import io.github.ingmargoudt.referee.game.Event;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Permanent;
import io.github.ingmargoudt.referee.game.abilities.TriggeredAbility;

import java.util.Arrays;
import java.util.Objects;

public class ComesIntoPlayTappedEffectUnlessAbility implements ReplacementEffect {

    @Override
    public boolean checkEvent(Event event, MagicObject source, MagicObject parentObject) {
        return event == Event.ENTERS_THE_BATTLEFIELD && Objects.equals(source, parentObject);

    }

    @Override
    public void apply(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> {
            if(player.choosesOption(Arrays.asList("Yes", "No")).equals("No")) {
                if (source instanceof Permanent) {
                    ((Permanent) source).tap();
                }
            }
        });
    }
}
