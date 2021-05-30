package io.github.ingmargoudt.referee.game;

public class Spell extends MagicObject implements Stackable{

    Card card;

    public Spell(Card card){
        super();
        this.card = card;
    }

    public void resolve(){
        
    }
}
