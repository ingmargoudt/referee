package io.github.ingmargoudt.referee.cards.f;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.CyclingAbility;
import io.github.ingmargoudt.referee.game.effects.DamageTargetEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.targets.TargetPlayer;

public class FlameJet extends Card {
    public FlameJet() {
        super("Flame Jet");
        cardtypes.add(CardType.SORCERY);
        spellEffects.addEffect(new DamageTargetEffect(2, new TargetPlayer()));
        abilities.add(new CyclingAbility());
    }
}
