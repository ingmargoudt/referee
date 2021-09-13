package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.Ability;

import java.util.UUID;

public abstract class Effect {

    private final UUID id;

    public Effect(){
        id = UUID.randomUUID();
    }

    public abstract void apply(Ability source, Game game);
}
