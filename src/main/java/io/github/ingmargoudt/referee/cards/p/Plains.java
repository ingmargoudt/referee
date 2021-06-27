package io.github.ingmargoudt.referee.cards.p;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;

public class Plains extends Card {
    public Plains() {
        super("Plains");
        getCardtypes().add(CardType.LAND);
        getSubTypes().add(SubType.Plains);
    }
}
