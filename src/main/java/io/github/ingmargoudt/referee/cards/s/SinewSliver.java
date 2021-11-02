package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.BoostAllCreatures;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.Filter;

public class SinewSliver extends Card {
    public SinewSliver() {
        super("Sinew Sliver");
        cardtypes.add(CardType.CREATURE);
        subTypes.add(SubType.SLIVER);
        setPower(1);
        setToughness(1);
        addAbility(new StaticAbility(new BoostAllCreatures(Filter.by(SubType.SLIVER), 1, 1)));
    }
}
