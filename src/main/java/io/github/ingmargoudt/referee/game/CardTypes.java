package io.github.ingmargoudt.referee.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CardTypes {

    private List<CardType> cardtypesList = new ArrayList<>();

    public boolean isPermanent() {
        return cardtypesList.contains(CardType.CREATURE);
    }

    public void add(CardType ... types){
        cardtypesList.addAll(Arrays.asList(types));

    }

    public CardTypes(){

    }

    public CardTypes(List<CardType> cardTypes){
        this.cardtypesList.addAll(cardTypes);
    }

    public boolean isCreature() {
        return cardtypesList.contains(CardType.CREATURE);
    }

    public void addAll(CardTypes cardtypes) {
        this.cardtypesList.addAll(cardtypes.cardtypesList);
    }

    public boolean isLand() {
        return cardtypesList.contains(CardType.LAND);
    }

    public void clear() {
        cardtypesList.clear();
    }
}
