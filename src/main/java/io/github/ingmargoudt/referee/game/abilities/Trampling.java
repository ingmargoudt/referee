package io.github.ingmargoudt.referee.game.abilities;

import lombok.Getter;

public class Trampling extends StaticAbility{
    @Getter
    private static final Trampling instance = new Trampling();

    private Trampling() {
        super();
    }
    @Override
    public String getRule(){
        return "Trampling";
    }
}
