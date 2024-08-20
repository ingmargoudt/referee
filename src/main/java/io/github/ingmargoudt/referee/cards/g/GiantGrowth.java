package io.github.ingmargoudt.referee.cards.g;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.BoostTargetCreatureEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.properties.DurationType;

public class GiantGrowth extends Card{
    public GiantGrowth() {
        super("Giant Growth");
        getCardtypes().add(CardType.INSTANT);
        getSpellEffects().addEffect(new BoostTargetCreatureEffect(3, 3, DurationType.UNTIL_END_OF_TURN));
    }
}