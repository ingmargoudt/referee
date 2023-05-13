package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.players.Player;

public interface Controllable {


    boolean isControlledBy(Player thePlayer);
}
