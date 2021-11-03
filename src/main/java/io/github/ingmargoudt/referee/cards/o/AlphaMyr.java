package io.github.ingmargoudt.referee.cards.o;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class AlphaMyr extends Card {
    public AlphaMyr() {
        super("Alpha Myr");
        cardtypes.add(CardType.ARTIFACT);
        cardtypes.add(CardType.CREATURE);
        setPower(2);
        setToughness(1);
    }
}
