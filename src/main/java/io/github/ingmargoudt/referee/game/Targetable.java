package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public interface Targetable {

    void damage(int amount);
    UUID getId();
}
