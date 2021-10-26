package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.effects.DrainTargetEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.TargetCreature;

public class SorinsThirst extends Card {
    public SorinsThirst() {
        super("Sorin's Thirst");
        cardtypes.add(CardType.INSTANT);
        spellEffects.add(new DrainTargetEffect( 2, new TargetCreature()));
    }
}
