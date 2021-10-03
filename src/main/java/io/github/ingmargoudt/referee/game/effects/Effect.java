package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;

import java.util.UUID;

public abstract class Effect {

    private final UUID id;

    protected Effect(){
        id = UUID.randomUUID();
    }

    public abstract void apply(MagicObject source, Game game);
}
