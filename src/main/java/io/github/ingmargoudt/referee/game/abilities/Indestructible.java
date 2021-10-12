package io.github.ingmargoudt.referee.game.abilities;

public class Indestructible extends StaticAbility {

    private static Indestructible instance = new Indestructible();

    private Indestructible() {
        super();
        System.out.println("indestrubctuel constructor");
    }

    public static Indestructible getInstance(){
        return instance;
    }


}
