package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.DurationType;
import io.github.ingmargoudt.referee.game.targets.Filter;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StaticAbility extends Ability {

    Effects<ContinuousEffect> effects = new Effects<>(ContinuousEffect.class);

    public StaticAbility() {
        super();
    }

    public StaticAbility(ContinuousEffect effect) {
        super();
        effects.addEffect(effect);
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        effects.apply(source, game);
    }

    @Override
    public String getRule() {
        return effects.getRule();
    }


}
