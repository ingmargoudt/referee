package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public class Card extends MagicObject{

    String name;
    UUID controller;
    UUID owner;

    public Card(String name){
        super();
        this.name = name;
    }
}