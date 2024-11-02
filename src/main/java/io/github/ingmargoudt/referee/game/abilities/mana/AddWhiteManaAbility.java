package io.github.ingmargoudt.referee.game.abilities.mana;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

public class AddWhiteManaAbility extends ActivatedManaAbility {

    private static final Effects<OneShotEffect> addManaEffect = new Effects<>(new AddManaEffect(ManaType.WHITE));

    public AddWhiteManaAbility(Cost... costs) {
        super(Costs.of(costs), addManaEffect);
    }
}
