package io.github.ingmargoudt.referee.game.objects;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BaseObject {

    protected final UUID id;

    public BaseObject(){
        id = UUID.randomUUID();
    }
}
