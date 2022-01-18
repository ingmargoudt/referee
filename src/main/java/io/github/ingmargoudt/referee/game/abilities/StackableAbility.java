package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

public abstract class StackableAbility extends Ability {

    protected Effects<OneShotEffect> effects = new Effects<>(OneShotEffect.class);

}
