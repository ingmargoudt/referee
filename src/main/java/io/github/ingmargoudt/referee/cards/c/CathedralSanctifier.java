package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.abilities.EntersTheBattlefieldAbility;
import io.github.ingmargoudt.referee.game.effects.YouGainLifeEffect;

public class CathedralSanctifier extends Card {

    public CathedralSanctifier(){
        super("Cathedral Sanctifier");
        getCardtypes().add(CardType.CREATURE);
        setPower(0);
        setToughness(3);
        getAbilities().add(new EntersTheBattlefieldAbility(new YouGainLifeEffect(3)));
    }
}
