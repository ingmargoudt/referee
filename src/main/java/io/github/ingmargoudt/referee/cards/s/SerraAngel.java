package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.statics.Flying;
import io.github.ingmargoudt.referee.game.abilities.statics.Vigilance;
import io.github.ingmargoudt.referee.game.objects.Card;

public class SerraAngel extends Card {
    public SerraAngel() {
        super("Serra Angel");
        addAbility(Flying.getInstance());
        addAbility(Vigilance.getInstance());
        cardtypes.add(CardType.CREATURE);
        setPower(4);
        setToughness(4);
    }
}
