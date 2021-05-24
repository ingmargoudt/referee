package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public class BaseObject {

    private final UUID id;

    public BaseObject(){
        id = UUID.randomUUID();
    }
}
