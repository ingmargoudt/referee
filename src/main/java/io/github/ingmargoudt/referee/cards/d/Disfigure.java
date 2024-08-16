package io.github.ingmargoudt.referee.cards.d;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.BoostTargetCreatureEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.properties.DurationType;

public class Disfigure extends Card {
    public Disfigure() {
        super("Disfigure");
        getCardtypes().add(CardType.INSTANT);
        getSpellEffects().addEffect(new BoostTargetCreatureEffect(-2, -2, DurationType.UNTIL_END_OF_TURN));
    }
}
