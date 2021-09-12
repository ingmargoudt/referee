package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class Ability {


    private MagicObject source;

    public Ability(MagicObject source){
        this.source = source;
    }

    public abstract void resolve(Game game);

    public UUID getController(){
        return source.getController();
    }


}
