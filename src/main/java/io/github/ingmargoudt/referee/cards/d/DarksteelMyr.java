package io.github.ingmargoudt.referee.cards.d;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.statics.Indestructible;
import io.github.ingmargoudt.referee.game.objects.Card;

public class DarksteelMyr extends Card {
    public DarksteelMyr() {
        super("Darksteel Myr");
        cardtypes.add(CardType.CREATURE);
        setPower(1);
        setToughness(3);
        addAbility(Indestructible.getInstance());
    }
}
