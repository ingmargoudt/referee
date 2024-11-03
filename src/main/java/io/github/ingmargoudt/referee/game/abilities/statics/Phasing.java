package io.github.ingmargoudt.referee.game.abilities.statics;

import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import lombok.Getter;

public class Phasing extends StaticAbility {
    @Getter
    private static final Phasing instance = new Phasing();

    private Phasing() {
        super();
    }

    @Override
    public String getRule(){
        return "Phasing";
    }
}
