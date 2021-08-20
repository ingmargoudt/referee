package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.Effects;

import java.util.ArrayList;
import java.util.UUID;

public class AddBlackManaAbility extends ActivatedManaAbility {

    private static final Effects addManaEffect = Effects.of(new AddManaEffect(ManaType.Black));

    public AddBlackManaAbility(UUID source) {
        super(new ArrayList<>(), addManaEffect, source);
    }
}
