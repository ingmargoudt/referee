package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaticAbility extends Ability {

    List<ContinuousEffect> effects = new ArrayList<>();

    public StaticAbility(ContinuousEffect effect, UUID source) {
        this.source = source;
        effects.add(effect);
    }

    public void apply(Game game) {
        for (ContinuousEffect continuousEffect : effects) {
            continuousEffect.apply(this, game);
        }
    }
}
