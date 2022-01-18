package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.zones.Zone;

public class ActivatedAbility extends StackableAbility {

    protected Costs costs;
    protected Zone activatedFrom = Zone.BATTLEFIELD;

    public ActivatedAbility(Costs costs, Effects<OneShotEffect> effects) {
        super();
        this.costs = costs;
        this.effects = effects;

    }

    public ActivatedAbility(Costs costs, Effect effect){
        this.costs = costs;
        effects = new Effects<>(OneShotEffect.class);
        effects.addEffect(effect);
    }

    public ActivatedAbility(Costs costs, Effect effect, Zone activatedFrom){
        this.costs = costs;
        effects = new Effects<>(OneShotEffect.class);
        effects.addEffect(effect);
        this.activatedFrom = activatedFrom;
    }

    public void payCosts(MagicObject source, Game game){
        costs.payAll(source, game);
    }


    @Override
    public void resolve(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> effects.apply(source, game));
    }

    @Override
    public String getRule() {
        return costs.getRule() + " " + effects.getRule();
    }

    public boolean canBePlayed(MagicObject object, Game game) {
        if(this.activatedFrom == game.locate(object)){
            return costs.canPay(object, game);
        }
        return false;
    }
}
