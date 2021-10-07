package io.github.ingmargoudt.referee.cards.m;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class Mountain extends Card {
    public Mountain() {
        super("Mountain");
        getCardtypes().add(CardType.LAND);
        getSubTypes().add(SubType.MOUNTAIN);
    }
}
