package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.ManaCost;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.BoostThisCreatureEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.properties.DurationType;

public class ShivanDragon extends Card {
    public ShivanDragon() {
        super("Shivan Dragon");
        cardtypes.add(CardType.CREATURE);
        subTypes.add(SubType.DRAGON);

        setPower(5);
        setToughness(5);
        addAbility(new ActivatedAbility(Costs.of(new ManaCost("{R}")), new BoostThisCreatureEffect(1, 0, DurationType.UNTIL_END_OF_TURN)));
    }
}
