package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class Ability {

    UUID id;



    protected Ability(){
        id = UUID.randomUUID();
    }

    public abstract void resolve(MagicObject source, Game game);



}
