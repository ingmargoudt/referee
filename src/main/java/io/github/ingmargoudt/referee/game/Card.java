package io.github.ingmargoudt.referee.game;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Card extends MagicObject{

    UUID controller;
    UUID owner;

    public Card(String name, UUID controller){
        super(name);
        this.controller = controller;
    }
}
