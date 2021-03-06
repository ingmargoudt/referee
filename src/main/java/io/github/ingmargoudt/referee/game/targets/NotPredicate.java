package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public class NotPredicate implements Predicate {

    Predicate predicate;

    private NotPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public static NotPredicate not(Predicate predicate) {
        return new NotPredicate(predicate);
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        return !predicate.evaluate(target, game, source);
    }

    public String getRule() {
        return "non" + predicate.getRule();
    }
}
