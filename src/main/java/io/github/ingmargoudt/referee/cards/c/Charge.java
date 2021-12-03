package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.BoostAllControlledCreatures;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.properties.DurationType;

public class Charge extends Card {
    public Charge() {
        super("Charge");
        cardtypes.add(CardType.INSTANT);
        spellEffects.addEffect(new BoostAllControlledCreatures(1, 1, DurationType.UNTIL_END_OF_TURN));
    }
}
