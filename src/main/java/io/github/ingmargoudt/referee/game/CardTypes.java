package io.github.ingmargoudt.referee.game;

import java.util.*;
public class CardTypes {

    List<CardType> cardTypes = new ArrayList<>();

    public boolean isPermanent() {
        return cardTypes.contains(CardType.CREATURE);
    }
}
