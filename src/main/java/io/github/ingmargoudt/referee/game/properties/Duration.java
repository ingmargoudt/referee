package io.github.ingmargoudt.referee.game.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Duration {

    int turn;
    DurationType durationType;

    public static Duration Continuous(){
        return new Duration(-1, DurationType.CONTINUOUS);
    }
}
