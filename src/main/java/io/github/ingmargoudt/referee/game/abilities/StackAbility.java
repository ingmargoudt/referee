package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Stackable;

import java.util.UUID;

public class StackAbility implements Stackable {

    StackableAbility stackableAbility;
    MagicObject source;

    public StackAbility(TriggeredAbility ability, MagicObject source) {
        this.stackableAbility = ability;
        this.source = source;
    }

    public StackAbility(ActivatedAbility ability, Permanent source){
        this.stackableAbility = ability;
        this.source = source;
    }


    @Override
    public void resolve(Game game) {
        stackableAbility.resolve(source, game);
    }

    @Override
    public UUID getController() {
        return source.getController();
    }

    @Override
    public UUID getId() {
        return stackableAbility.getId();
    }

    @Override
    public MagicObject getSource() {
        return source;
    }

    @Override
    public String getName() {
        return stackableAbility.getClass().getSimpleName() + " of " + source.getName();
    }

    @Override
    public boolean hasTargets() {
        return false;
    }

    @Override
    public Effects<OneShotEffect> getEffects() {
        return stackableAbility.effects;
    }
}
