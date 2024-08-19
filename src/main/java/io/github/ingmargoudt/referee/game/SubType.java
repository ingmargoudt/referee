package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.targets.Predicate;
import io.github.ingmargoudt.referee.game.targets.SubTypePredicate;

public enum SubType implements Predicatable {

    PLAINS, MOUNTAIN, ISLAND, FOREST, SLIVER, SWAMP, ANGEL, BAT,BIRD, DJINN, DRAGON;

    @Override
    public Predicate getPredicate() {
        return new SubTypePredicate(this);
    }
}
