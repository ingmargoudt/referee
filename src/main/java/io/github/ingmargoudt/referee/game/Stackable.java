package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public interface Stackable {

    void resolve(Game game);
    UUID getController();

    String getName();
}
