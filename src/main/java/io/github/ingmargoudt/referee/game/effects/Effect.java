package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.Ability;

import java.util.UUID;

public abstract class Effect {

    UUID id;
    UUID source;


    public abstract void apply(Ability source, Game game);
}
