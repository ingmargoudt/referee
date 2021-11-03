package io.github.ingmargoudt.referee.cards.a;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.CounterEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.Filter;
import io.github.ingmargoudt.referee.game.targets.TargetSpell;

import static io.github.ingmargoudt.referee.game.targets.OrPredicate.or;

public class Annul extends Card {
    public Annul() {
        super("Annul");
        cardtypes.add(CardType.INSTANT);
        spellEffects.add(new CounterEffect(new TargetSpell(Filter.by(or(CardType.ENCHANTMENT, CardType.ARTIFACT)))));
    }
}
