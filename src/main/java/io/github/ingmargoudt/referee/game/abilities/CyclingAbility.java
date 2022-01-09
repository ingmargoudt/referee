package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.ManaCost;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.cost.DiscardThisCardCost;
import io.github.ingmargoudt.referee.game.effects.DrawCardEffect;
import io.github.ingmargoudt.referee.game.zones.Zone;

public class CyclingAbility extends ActivatedAbility {
    public CyclingAbility() {
        super(Costs.of(new ManaCost("{2}"), new DiscardThisCardCost()), new DrawCardEffect(), Zone.HAND);

    }
}
