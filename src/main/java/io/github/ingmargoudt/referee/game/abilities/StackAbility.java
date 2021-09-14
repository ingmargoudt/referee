package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Stackable;

import java.util.UUID;

public class StackAbility implements Stackable {

    TriggeredAbility triggeredAbility;
    MagicObject source;

    public StackAbility(TriggeredAbility ability, MagicObject source){
        this.triggeredAbility = ability;
        this.source = source;
    }

    @Override
    public void resolve(Game game) {
        triggeredAbility.resolve(source, game);
    }

    @Override
    public UUID getController() {
        return source.getController();
    }

    @Override
    public String getName() {
        return triggeredAbility.getClass().getSimpleName() + " of " + source.getName();
    }
}
