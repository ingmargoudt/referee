package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public class BaseObject {

    protected final UUID id;

    public BaseObject(){
        id = UUID.randomUUID();
    }
}
