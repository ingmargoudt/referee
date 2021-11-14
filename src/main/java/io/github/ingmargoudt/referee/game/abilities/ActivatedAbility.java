package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.List;

public class ActivatedAbility extends Ability {

    protected Costs costs;
    protected List<OneShotEffect> effects;

    public ActivatedAbility(Costs costs, List<OneShotEffect> effects) {
        super();
        this.costs = costs;
        this.effects = effects;

    }


    @Override
    public void resolve(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> effects.forEach(effect -> effect.apply(source, game)));
    }

    @Override
    public String getRule(){
        return costs.toString() + " " + effects.toString();
    }

}
