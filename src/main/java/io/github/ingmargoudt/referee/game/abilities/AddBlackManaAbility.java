package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

public class AddBlackManaAbility extends ActivatedManaAbility {

    private static final Effects<OneShotEffect> addManaEffect = new Effects<>(new AddManaEffect(ManaType.BLACK));

    public AddBlackManaAbility(Cost... cost) {
        super(Costs.of(cost), addManaEffect);
    }
}
