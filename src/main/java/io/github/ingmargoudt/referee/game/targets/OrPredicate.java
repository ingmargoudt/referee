package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Predicatable;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.game.properties.Targetable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrPredicate implements Predicate, Ruleable {

    List<Predicate> predicates;

    private OrPredicate(Predicatable... predicate) {
        this.predicates = Arrays.stream(predicate).map(Predicatable::getPredicate).collect(Collectors.toList());
    }

    public static OrPredicate or(Predicatable... predicate) {
        return new OrPredicate(predicate);
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        return predicates.stream().anyMatch(predicate -> predicate.evaluate(target, game, source));
    }

    public String getRule() {
        return predicates.stream().map(Predicate::getRule).collect(Collectors.joining(" or "));
    }
}
