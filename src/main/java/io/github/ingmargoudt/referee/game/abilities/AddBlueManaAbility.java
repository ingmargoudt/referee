package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

import java.util.Arrays;
import java.util.List;

public class AddBlueManaAbility extends ActivatedManaAbility {

    private static final List<OneShotEffect> addManaEffect = Arrays.asList(new AddManaEffect(ManaType.BLUE));

    public AddBlueManaAbility(Cost... costs) {
        super(Costs.of(costs), addManaEffect);
    }
}