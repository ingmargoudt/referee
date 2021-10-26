package io.github.ingmargoudt.referee.cards.e;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.CounterEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.Filter;
import io.github.ingmargoudt.referee.game.targets.TargetSpell;

public class EssenceScatter extends Card {

    public EssenceScatter() {
        super("Essence Scatter");
        cardtypes.add(CardType.INSTANT);
        getSpellEffects().add(new CounterEffect(new TargetSpell(Filter.by(CardType.CREATURE.getPredicate()))));
    }
}
