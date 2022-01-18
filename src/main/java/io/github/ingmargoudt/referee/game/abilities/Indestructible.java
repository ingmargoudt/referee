package io.github.ingmargoudt.referee.game.abilities;

public class Indestructible extends StaticAbility {

    private static final Indestructible instance = new Indestructible();

    private Indestructible() {
        super();
    }

    public static Indestructible getInstance() {
        return instance;
    }

    @Override
    public String getRule(){
        return "Indestructible";
    }


}
