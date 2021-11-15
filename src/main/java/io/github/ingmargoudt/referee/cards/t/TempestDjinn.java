package io.github.ingmargoudt.referee.cards.t;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.SuperType;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.BoostThisCreatureEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.ControlledByPlayerSelector;
import io.github.ingmargoudt.referee.game.targets.Filter;

public class TempestDjinn extends Card {
    public TempestDjinn() {
        super("Tempest Djinn");
        cardtypes.add(CardType.CREATURE);
        subTypes.add(SubType.DJINN);
        setToughness(4);
        abilities.add(new StaticAbility(new BoostThisCreatureEffect(1, 0, Filter.by(SuperType.BASIC, SubType.ISLAND, ControlledByPlayerSelector.YOU))));
    }
}


