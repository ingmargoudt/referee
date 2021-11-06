package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.targets.Predicate;
import io.github.ingmargoudt.referee.game.targets.SuperTypePredicate;

public enum SuperType implements Predicatable {

    BASIC;

    @Override
    public Predicate getPredicate() {
        return new SuperTypePredicate(this);
    }
}
