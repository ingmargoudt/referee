package io.github.ingmargoudt.referee.game.abilities;

public class Flying extends StaticAbility {

    private static final Flying instance = new Flying();

    private Flying() {
        super();
    }

    public static Flying getInstance() {
        return instance;
    }

    @Override
    public String getRule(){
        return "Flying";
    }
}
