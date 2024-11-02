package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.triggered.EntersTheBattlefieldAbility;
import io.github.ingmargoudt.referee.game.effects.YouGainLifeEffect;
import io.github.ingmargoudt.referee.game.objects.Card;

public class CathedralSanctifier extends Card {

    public CathedralSanctifier() {
        super("Cathedral Sanctifier");
        getCardtypes().add(CardType.CREATURE);
        setPower(0);
        setToughness(3);
        getAbilities().add(new EntersTheBattlefieldAbility(new YouGainLifeEffect(3)));
    }
}
