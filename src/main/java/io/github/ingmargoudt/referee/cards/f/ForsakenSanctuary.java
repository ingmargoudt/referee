package io.github.ingmargoudt.referee.cards.f;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.AddBlackManaAbility;
import io.github.ingmargoudt.referee.game.abilities.AddWhiteManaAbility;
import io.github.ingmargoudt.referee.game.cost.TapCost;
import io.github.ingmargoudt.referee.game.effects.ComesIntoPlayTappedEffect;
import io.github.ingmargoudt.referee.game.objects.Card;

public class ForsakenSanctuary extends Card {
    public ForsakenSanctuary() {
        super("Forsaken Sanctuary");
        cardtypes.add(CardType.LAND);
        addAbility(new AddBlackManaAbility(new TapCost()));
        addAbility(new AddWhiteManaAbility(new TapCost()));
        getReplacementEffects().add(new ComesIntoPlayTappedEffect());
    }
}
