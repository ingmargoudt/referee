package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import lombok.Getter;

public class ContinuousEffectInitializer extends OneShotEffect{

    @Getter
    ContinuousEffect continuousEffect;
    public ContinuousEffectInitializer(ContinuousEffect continuousEffect){
        this.continuousEffect = continuousEffect;
    }

    @Override
    public void apply(MagicObject object, Game game) {
        game.addEffect(object, continuousEffect);
    }

    @Override
    public String getRule() {
        return continuousEffect.getRule();
    }
}
