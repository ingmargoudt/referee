package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.List;

public class StaticAbility extends Ability {

    List<ContinuousEffect> effects = new ArrayList<>();

    public StaticAbility(ContinuousEffect effect) {
        effects.add(effect);
    }

    public void apply(Player player, Game game) {
        for (ContinuousEffect continuousEffect : effects) {
            continuousEffect.apply(player, game);
        }
    }
}
