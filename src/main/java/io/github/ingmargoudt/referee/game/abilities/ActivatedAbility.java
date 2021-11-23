package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.List;

public class ActivatedAbility extends Ability {

    protected Costs costs;
    protected Effects<OneShotEffect> effects;

    public ActivatedAbility(Costs costs, Effects<OneShotEffect> effects) {
        super();
        this.costs = costs;
        this.effects = effects;

    }


    @Override
    public void resolve(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> effects.apply(source, game));
    }

    @Override
    public String getRule() {
        return costs.getRule() + " " + effects.getRule();
    }

}
