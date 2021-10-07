package io.github.ingmargoudt.referee.cards.a;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.YouGainLifeEffect;
import io.github.ingmargoudt.referee.game.objects.Card;

public class AngelsMercy extends Card {

    public AngelsMercy() {
        super("Angel's Mercy");
        getCardtypes().add(CardType.INSTANT);
        getSpellEffects().add(new YouGainLifeEffect(7));
    }
}
