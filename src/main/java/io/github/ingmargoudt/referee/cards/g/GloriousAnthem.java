package io.github.ingmargoudt.referee.cards.g;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.BoostAllControlledCreatures;

public class GloriousAnthem extends Card {


    public GloriousAnthem() {
        super("Glorious Anthem");
        getAbilities().add(new StaticAbility(new BoostAllControlledCreatures(1, 1)));
    }
}
