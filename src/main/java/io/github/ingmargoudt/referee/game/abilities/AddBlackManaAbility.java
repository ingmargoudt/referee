package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.Effects;

import java.util.ArrayList;

public class AddBlackManaAbility extends ActivatedManaAbility {

    private static final Effects addManaEffect = Effects.of(new AddManaEffect(ManaType.BLACK));

    public AddBlackManaAbility() {
        super(new ArrayList<>(), addManaEffect);
    }
}
