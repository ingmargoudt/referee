package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.effects.Effects;

import java.util.ArrayList;
import java.util.List;

public class ActivatedAbility extends Ability{

    protected List<Cost> costs = new ArrayList<>();
    protected Effects effects;

    public ActivatedAbility(List<Cost> costsList, Effects effects){
        super();
     this.costs = costsList;
     this.effects = effects;

    }



    @Override
    public void resolve(MagicObject source, Game game) {

    }
}
