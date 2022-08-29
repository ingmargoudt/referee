package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.players.Player;

import java.util.UUID;

public interface Targetable {

    UUID getId();

    String getName();

    Player getController();
}
