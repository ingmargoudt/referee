package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StaticAbility extends Ability {

    List<ContinuousEffect> effects = new ArrayList<>();

    public StaticAbility() {
        super();
    }

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

    @Override
    public String getRule() {
        return effects.stream().map(Ruleable::getRule).collect(Collectors.joining());
    }
}
