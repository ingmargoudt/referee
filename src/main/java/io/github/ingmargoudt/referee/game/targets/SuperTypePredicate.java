package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SuperType;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public class SuperTypePredicate implements Predicate {

    SuperType theSuperType;

    public SuperTypePredicate(SuperType superType) {
        this.theSuperType = superType;
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        if (target instanceof Permanent permanent) {
            return permanent.getSuperTypes().has(theSuperType);
        }
        return false;
    }

    public String getRule() {
        return theSuperType.toString().toLowerCase();
    }
}
