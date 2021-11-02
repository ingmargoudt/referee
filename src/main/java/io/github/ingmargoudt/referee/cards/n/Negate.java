package io.github.ingmargoudt.referee.cards.n;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.CounterEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.Filter;
import io.github.ingmargoudt.referee.game.targets.TargetSpell;

import static io.github.ingmargoudt.referee.game.targets.NotPredicate.not;

public class Negate extends Card {
    public Negate() {
        super("Negate");
        cardtypes.add(CardType.INSTANT);
        getSpellEffects().add(new CounterEffect(new TargetSpell(Filter.by(not(CardType.CREATURE.getPredicate())))));
    }
}
