package io.github.ingmargoudt.referee.game;

public enum ManaType {
    WHITE("W"),
    BLUE("U"),
    BLACK("B"),
    RED("R"),
    GREEN("G"),
    COLORLESS("[]");

    String letter;

    ManaType(String letter){
        this.letter= letter;
    }

    public String getLetter(){
        return letter;
    }


}
