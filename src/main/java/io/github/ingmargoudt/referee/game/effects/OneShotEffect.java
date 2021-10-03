package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.Stackable;
import io.github.ingmargoudt.referee.game.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class OneShotEffect {

    private final UUID id;


    List<Target> targets = new ArrayList<>();
    protected OneShotEffect(){
        id = UUID.randomUUID();
    }


    public abstract void apply(MagicObject object, Game game);

    public void choose(Stackable stackable, Game game){
        targets.get(0).choose(stackable.getController(), game);
    }

    public boolean hasTargets(){
        return !targets.isEmpty();
    }
}
