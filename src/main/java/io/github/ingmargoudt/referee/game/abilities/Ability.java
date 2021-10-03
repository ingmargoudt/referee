package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import lombok.Getter;

@Getter
public abstract class Ability {



    protected Ability(){
    }

    public abstract void resolve(MagicObject source, Game game);



}
