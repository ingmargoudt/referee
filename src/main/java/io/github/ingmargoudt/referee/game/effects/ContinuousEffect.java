package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.UUID;

public abstract class ContinuousEffect {

    private final UUID id;

    protected ContinuousEffect() {
        id = UUID.randomUUID();
    }

    public abstract void apply(MagicObject source, Game game);

}
