package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.CounterEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.TargetSpell;

public class CounterSpell extends Card {
    public CounterSpell() {
        super("Counterspell");
        cardtypes.add(CardType.INSTANT);
        getSpellEffects().addEffect(new CounterEffect(new TargetSpell()));
    }
}
