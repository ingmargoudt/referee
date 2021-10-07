package io.github.ingmargoudt.referee.cards.f;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.AddBlackManaAbility;
import io.github.ingmargoudt.referee.game.abilities.AddWhiteManaAbility;
import io.github.ingmargoudt.referee.game.effects.ComesIntoPlayTappedEffect;
import io.github.ingmargoudt.referee.game.objects.Card;

public class ForsakenSanctuary extends Card {
    public ForsakenSanctuary() {
        super("Forsaken Sanctuary");
        cardtypes.add(CardType.LAND);
        addAbility(new AddBlackManaAbility());
        addAbility(new AddWhiteManaAbility());
        getReplacementEffects().add(new ComesIntoPlayTappedEffect());
    }
}
