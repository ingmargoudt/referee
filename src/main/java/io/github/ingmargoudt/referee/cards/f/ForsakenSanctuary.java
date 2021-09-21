package io.github.ingmargoudt.referee.cards.f;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.abilities.AddBlackManaAbility;
import io.github.ingmargoudt.referee.game.abilities.AddWhiteManaAbility;
import io.github.ingmargoudt.referee.game.effects.ComesIntoPlayTappedEffect;

public class ForsakenSanctuary extends Card {
    public ForsakenSanctuary() {
        super("Forsaken Sanctuary");
        addAbility(new AddBlackManaAbility());
        addAbility(new AddWhiteManaAbility());
        getReplacementEffects().add(new ComesIntoPlayTappedEffect());
    }
}
