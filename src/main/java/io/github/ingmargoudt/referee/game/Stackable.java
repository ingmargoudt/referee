package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public interface Stackable {

    void resolve();
    UUID getController();

    String getName();
}
