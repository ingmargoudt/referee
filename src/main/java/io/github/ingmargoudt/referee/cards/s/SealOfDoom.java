package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Color;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.cost.SacrificeSourceCost;
import io.github.ingmargoudt.referee.game.effects.DestroyTargetEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.*;

public class SealOfDoom extends Card {
    public SealOfDoom() {
        super("Seal of Doom");
        cardtypes.add(CardType.ENCHANTMENT);
        abilities.add(new ActivatedAbility(Costs.of(new SacrificeSourceCost()), new DestroyTargetEffect(new TargetCreature(Filter.by(NotPredicate.not(Color.BLACK.getPredicate()))))));
    }
}
