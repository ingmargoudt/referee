package io.github.ingmargoudt.referee.game;

import java.util.*;
public class CardTypes {

    List<CardType> cardTypes = new ArrayList<>();

    public boolean isPermanent() {
        return cardTypes.contains(CardType.CREATURE);
    }

    public void add(CardType ... types){
        cardTypes.addAll(Arrays.asList(types));

    }

    public CardTypes(){

    }

    public CardTypes(List<CardType> cardTypes){
        this.cardTypes.addAll(cardTypes);
    }

    public boolean isCreature() {
        return cardTypes.contains(CardType.CREATURE);
    }

    public void addAll(CardTypes cardtypes) {
        this.cardTypes.addAll(cardtypes.cardTypes);
    }
}
