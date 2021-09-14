package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class Ability {



    public Ability(){
    }

    public abstract void resolve(MagicObject source, Game game);



}
