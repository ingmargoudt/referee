package io.github.ingmargoudt.referee.game;

public class Permanent extends MagicObject{

    Card card;
    boolean isTapped;

    public Permanent(Card card){
        super();
        this.card = card;
    }

    public void tap(){
        isTapped = true;
    }

    public void untap(){
        isTapped = false;
    }
}
