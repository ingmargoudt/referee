package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.Effect;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class Ability {


    protected UUID source;

    public abstract void resolve(Game game);


}
