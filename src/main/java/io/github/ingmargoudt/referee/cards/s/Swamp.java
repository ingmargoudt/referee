package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class Swamp extends Card {
    public Swamp() {
        super("Swamp");
        getCardtypes().add(CardType.LAND);
        getSubTypes().add(SubType.SWAMP);
    }
}
