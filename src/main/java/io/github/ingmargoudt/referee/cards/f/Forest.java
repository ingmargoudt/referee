package io.github.ingmargoudt.referee.cards.f;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class Forest extends Card {
    public Forest() {
        super("Forest");
        cardtypes.add(CardType.LAND);
        subTypes.add(SubType.FOREST);
    }
}
