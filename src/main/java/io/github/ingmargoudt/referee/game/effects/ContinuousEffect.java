package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.targets.Filter;

import java.util.UUID;

public abstract class ContinuousEffect extends Effect {

    private final UUID id;

    Filter filter;

    protected ContinuousEffect() {
        id = UUID.randomUUID();
        filter = Filter.empty();
    }


}
