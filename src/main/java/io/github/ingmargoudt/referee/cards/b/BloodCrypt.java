package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.EntersTheBattlefieldAbility;
import io.github.ingmargoudt.referee.game.effects.ComesIntoPlayTappedEffectUnlessAbility;

public class BloodCrypt extends Card {
    public BloodCrypt() {
        super("Blood Crypt");
        cardtypes.add(CardType.LAND);
        subTypes.add(SubType.MOUNTAIN);
        subTypes.add(SubType.SWAMP);
        getReplacementEffects().add(new ComesIntoPlayTappedEffectUnlessAbility());
    }
}
