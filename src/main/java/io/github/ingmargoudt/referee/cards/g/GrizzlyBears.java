package io.github.ingmargoudt.referee.cards.g;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.objects.Card;

public class GrizzlyBears extends Card {
    public GrizzlyBears() {
        super("Grizzly Bears","{1}{G}");
        getCardtypes().add(CardType.CREATURE);
        setPower(2);
        setToughness(2);
    }
}
