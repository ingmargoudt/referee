package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.targets.CardTypePredicate;
import io.github.ingmargoudt.referee.game.targets.Predicate;

public enum CardType implements Predicatable {
    CREATURE,
    ENCHANTMENT,
    INSTANT,
    LAND;

    public Predicate getPredicate() {
        return new CardTypePredicate(this);
    }
}
