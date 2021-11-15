package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Predicatable;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.game.properties.Targetable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter implements Ruleable {

    List<Predicate> predicates = new ArrayList<>();

    private Filter() {

    }

    public static Filter by(Predicate predicate) {
        return new Filter().addPredicate(predicate);
    }

    public static Filter by(Predicatable predicatable) {
        return new Filter().addPredicate(predicatable.getPredicate());
    }

    public static Filter by(Predicatable... predicatables) {
        Filter newFilter = new Filter();
        for (Predicatable predicatable : predicatables) {
            newFilter.addPredicate(predicatable.getPredicate());
        }
        return newFilter;
    }

    public static Filter empty() {
        return new Filter();
    }

    public Filter addPredicate(Predicate predicate) {
        predicates.add(predicate);
        return this;
    }

    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        return predicates.stream().allMatch(predicate -> predicate.evaluate(target, game, source));
    }

    public String getRule() {
        return predicates.stream().map(Predicate::getRule).collect(Collectors.joining(" "));
    }
}
