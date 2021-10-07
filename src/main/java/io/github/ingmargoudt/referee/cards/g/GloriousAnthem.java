package io.github.ingmargoudt.referee.cards.g;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.BoostAllControlledCreatures;
import io.github.ingmargoudt.referee.game.objects.Card;

public class GloriousAnthem extends Card {


    public GloriousAnthem() {
        super("Glorious Anthem");
        getCardtypes().add(CardType.ENCHANTMENT);
        getAbilities().add(new StaticAbility(new BoostAllControlledCreatures(1, 1)));
    }
}
