package io.github.ingmargoudt.referee.cards.l;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.DamageTargetEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.TargetAny;

public class LightningBolt extends Card {
    public LightningBolt() {
        super("Lightning Bolt");
        getCardtypes().add(CardType.INSTANT);
        getSpellEffects().addEffect(new DamageTargetEffect(3, new TargetAny()));
    }
}
