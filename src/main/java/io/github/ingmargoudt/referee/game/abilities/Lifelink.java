package io.github.ingmargoudt.referee.game.abilities;

import lombok.Getter;

public class Lifelink extends StaticAbility{
    @Getter
    private static final Lifelink instance = new Lifelink();

    private Lifelink() {
        super();
    }

    @Override
    public String getRule(){
        return "Lifelink";
    }
}
