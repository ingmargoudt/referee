package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public class SubTypePredicate implements Predicate {

    SubType theSubType;

    public SubTypePredicate(SubType subType) {
        this.theSubType = subType;
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        if (target instanceof Permanent) {
            return ((Permanent) target).getSubTypes().has(theSubType);
        }
        return false;
    }

    public String getRule() {
        return theSubType.toString().charAt(0)+ theSubType.toString().substring(1).toLowerCase()+ " ";
    }
}
