package io.github.ingmargoudt.referee.game.properties;

import lombok.Getter;

public enum DurationType {

    CONTINUOUS(0),
    UNTIL_END_OF_TURN(0);

    @Getter
    int offset;

    DurationType(int offset){
        this.offset = offset;
    }
}
