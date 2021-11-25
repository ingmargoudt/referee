package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.targets.CardTypePredicate;
import io.github.ingmargoudt.referee.game.targets.Predicate;

public enum CardType implements Predicatable {
    CREATURE,
    ENCHANTMENT,
    ARTIFACT,
    INSTANT,
    LAND, SORCERY;

    public Predicate getPredicate() {
        return new CardTypePredicate(this);
    }
}
