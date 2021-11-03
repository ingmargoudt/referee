package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Predicatable;
import io.github.ingmargoudt.referee.game.properties.Targetable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrPredicate implements Predicate {

    List<Predicate> predicates;

    private OrPredicate(Predicatable... predicate) {
        this.predicates = Arrays.stream(predicate).map(Predicatable::getPredicate).collect(Collectors.toList());
    }

    public static OrPredicate or(Predicatable... predicate) {
        return new OrPredicate(predicate);
    }

    @Override
    public boolean evaluate(Targetable target, Game game) {
        return predicates.stream().anyMatch(predicate -> predicate.evaluate(target, game));
    }
}
