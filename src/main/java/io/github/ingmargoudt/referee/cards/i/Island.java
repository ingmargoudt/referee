package io.github.ingmargoudt.referee.cards.i;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.SuperType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class Island extends Card {
    public Island() {
        super("Island");
        cardtypes.add(CardType.LAND);
        superTypes.add(SuperType.BASIC);
        subTypes.add(SubType.ISLAND);
    }
}
