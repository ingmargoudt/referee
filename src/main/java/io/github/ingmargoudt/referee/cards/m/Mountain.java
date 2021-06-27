package io.github.ingmargoudt.referee.cards.m;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;

public class Mountain extends Card {
    public Mountain() {
        super("Mountain");
        getCardtypes().add(CardType.LAND);
        getSubTypes().add(SubType.Mountain);
    }
}
