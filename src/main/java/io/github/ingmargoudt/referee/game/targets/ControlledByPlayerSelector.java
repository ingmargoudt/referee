package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Predicatable;

public enum ControlledByPlayerSelector implements Predicatable {

    YOU;

    @Override
    public Predicate getPredicate() {
        return new ControlledByPlayerPredicate(this);
    }
}
