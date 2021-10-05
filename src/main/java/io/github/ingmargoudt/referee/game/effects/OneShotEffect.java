package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class OneShotEffect {

    private final UUID id;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }


    List<Target> targets = new ArrayList<>();

    protected OneShotEffect() {
        id = UUID.randomUUID();
    }


    public abstract void apply(MagicObject object, Game game);

    public void choose(Stackable stackable, Game game) {
        targets.forEach(target -> target.choose(stackable, game, this));
    }

    public boolean hasTargets() {
        return !targets.isEmpty();
    }
}
