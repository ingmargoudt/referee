package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class Ability implements Ruleable {

    UUID id;


    protected Ability() {
        id = UUID.randomUUID();
    }

    public abstract void resolve(MagicObject source, Game game);


}
