package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.effects.BoostThisCreatureEffect;
import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.properties.DurationType;

public enum CounterType {
    P1P1(new BoostThisCreatureEffect(1,1, DurationType.CONTINUOUS));


    private final Effect effect;

    CounterType(Effect effect){
        this.effect = effect;
    }
}
