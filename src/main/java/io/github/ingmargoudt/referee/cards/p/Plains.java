package io.github.ingmargoudt.referee.cards.p;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class Plains extends Card {
    public Plains() {
        super("Plains");
        getCardtypes().add(CardType.LAND);
        getSubTypes().add(SubType.PLAINS);
    }
}
