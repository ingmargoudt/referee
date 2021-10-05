package io.github.ingmargoudt.referee.cards.l;

import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.DamageToAnyTargetEffect;

public class LightningBolt extends Card {
    public LightningBolt() {
        super("Lightning Bolt");
        getCardtypes().add(CardType.INSTANT);
        getSpellEffects().add(new DamageToAnyTargetEffect(3));
    }
}
