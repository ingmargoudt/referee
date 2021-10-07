package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.ArrayList;
import java.util.List;

public class StaticAbility extends Ability {

    List<ContinuousEffect> effects = new ArrayList<>();

    public StaticAbility(ContinuousEffect effect) {
        super();
        effects.add(effect);
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        for (ContinuousEffect continuousEffect : effects) {
            continuousEffect.apply(source, game);
        }
    }
}
