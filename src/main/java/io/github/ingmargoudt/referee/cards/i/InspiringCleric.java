package io.github.ingmargoudt.referee.cards.i;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.EntersTheBattlefieldAbility;
import io.github.ingmargoudt.referee.game.effects.YouGainLifeEffect;
import io.github.ingmargoudt.referee.game.objects.Card;

public class InspiringCleric extends Card {
    public InspiringCleric() {
        super("Inspiring Cleric");
        cardtypes.add(CardType.CREATURE);
        setPower(3);
        setToughness(2);
        addAbility(new EntersTheBattlefieldAbility(new YouGainLifeEffect(4)));
    }
}
