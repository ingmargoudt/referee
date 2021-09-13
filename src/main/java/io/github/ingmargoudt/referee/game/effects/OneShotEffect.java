package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;

import java.util.UUID;

public abstract class OneShotEffect {

    private final UUID id;
    public OneShotEffect(){
        id = UUID.randomUUID();
    }


    public abstract void apply(MagicObject object, Game game);
}
