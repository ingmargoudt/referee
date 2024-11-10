package io.github.ingmargoudt.referee.game.abilities.statics;

import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import lombok.Getter;

public class Vigilance extends StaticAbility {

    @Getter
    private static final Vigilance instance = new Vigilance();

    private Vigilance() {
        super();
    }
    @Override
    public String getRule(){
        return "Vigilance";
    }
}

