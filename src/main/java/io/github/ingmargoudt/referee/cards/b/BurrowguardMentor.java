package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.abilities.statics.Trampling;
import io.github.ingmargoudt.referee.game.effects.BoostThisCreatureForEachEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.ControlledByPlayerSelector;
import io.github.ingmargoudt.referee.game.targets.Filter;

public class BurrowguardMentor extends Card {
    public BurrowguardMentor() {
        super("Burrowguard Mentor");
        cardtypes.add(CardType.CREATURE);
        abilities.add(Trampling.getInstance());
        abilities.add(new StaticAbility(new BoostThisCreatureForEachEffect(1, 1, Filter.by(CardType.CREATURE, ControlledByPlayerSelector.YOU))));

    }
}
