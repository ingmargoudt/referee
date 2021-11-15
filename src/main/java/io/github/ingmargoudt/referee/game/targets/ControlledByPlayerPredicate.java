package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public class ControlledByPlayerPredicate implements Predicate {

    ControlledByPlayerSelector thePlayerSelector;

    public ControlledByPlayerPredicate(ControlledByPlayerSelector playerSelector) {
        this.thePlayerSelector = playerSelector;
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        if (thePlayerSelector == ControlledByPlayerSelector.YOU) {
            return source.getController().equals(target.getController());
        }
        return false;
    }

    public String getRule() {
        switch (thePlayerSelector) {
            case YOU:
                return "you control";

        }
        return "";
    }
}
