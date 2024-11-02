package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.statics.FirstStrike;
import io.github.ingmargoudt.referee.game.abilities.statics.Lifelink;
import io.github.ingmargoudt.referee.game.objects.Card;

public class BrightbladeStoat extends Card {
    public BrightbladeStoat() {
        super("Brightblade Stoat");
        cardtypes.add(CardType.CREATURE);
        setPower(2);
        setToughness(2);
        addAbility(FirstStrike.getInstance());
        addAbility(Lifelink.getInstance());
    }
}
