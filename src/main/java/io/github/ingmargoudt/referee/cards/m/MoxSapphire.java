package io.github.ingmargoudt.referee.cards.m;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.abilities.mana.ActivatedManaAbility;
import io.github.ingmargoudt.referee.game.abilities.mana.AddBlueManaAbility;
import io.github.ingmargoudt.referee.game.cost.TapCost;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.objects.Card;

public class MoxSapphire extends Card {
    public MoxSapphire(String name) {
        super(name, "{0}}");
        this.addAbility(new AddBlueManaAbility(new TapCost()));

    }
}
