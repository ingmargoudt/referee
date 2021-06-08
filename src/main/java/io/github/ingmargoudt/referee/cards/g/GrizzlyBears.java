package io.github.ingmargoudt.referee.cards.g;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.CardType;

import java.util.UUID;

public class GrizzlyBears extends Card {
    public GrizzlyBears() {
        super("Grizzly Bears");
        getCardtypes().add(CardType.CREATURE);
        setPower(2);
        setToughness(2);
    }
}
