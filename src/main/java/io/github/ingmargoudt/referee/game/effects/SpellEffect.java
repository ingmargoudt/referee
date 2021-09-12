package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Spell;
import io.github.ingmargoudt.referee.game.abilities.Ability;

import java.util.UUID;

public abstract class SpellEffect {

    UUID id;
    UUID source;


    public abstract void apply(Spell spell, Game game);
}
