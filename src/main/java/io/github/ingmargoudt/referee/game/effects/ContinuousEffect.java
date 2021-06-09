package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.players.Player;

public abstract class ContinuousEffect extends Effect {



    public abstract void apply(Player controller, Game game);
}
