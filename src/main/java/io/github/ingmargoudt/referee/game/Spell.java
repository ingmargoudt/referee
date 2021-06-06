package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public class Spell extends MagicObject implements Stackable{

    Card card;

    public Spell(Card card){
        super(card.getName());
        this.card = card;
    }

    public void resolve(){
        
    }

    public UUID getController(){
        return card.controller;
    }

    public String getName(){
        return card.getName();
    }
}
