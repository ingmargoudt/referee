package io.github.ingmargoudt.referee.game;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Card extends MagicObject{

    @Setter
    UUID controller;
    UUID owner;

    public Card(String name){
        super(name);
    }

    public boolean isPermanent() {
        return getCardtypes().isPermanent();
    }
}
