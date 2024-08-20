package io.github.ingmargoudt.referee.game.abilities;

import lombok.Getter;

public class FirstStrike extends StaticAbility{
    @Getter
    private static final FirstStrike instance = new FirstStrike();

    private FirstStrike() {
        super();
    }

    @Override
    public String getRule(){
        return "First strike";
    }
}
