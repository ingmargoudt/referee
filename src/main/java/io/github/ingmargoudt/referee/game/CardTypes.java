package io.github.ingmargoudt.referee.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardTypes {

    private final List<CardType> cardtypesList = new ArrayList<>();

    public CardTypes() {

    }

    public int count(){
        return cardtypesList.size();
    }


    public boolean isPermanent() {
        return cardtypesList.contains(CardType.CREATURE) || cardtypesList.contains(CardType.LAND) || cardtypesList.contains(CardType.ENCHANTMENT);
    }

    public void add(CardType... types) {
        cardtypesList.addAll(Arrays.asList(types));
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

    public boolean has(CardType cardType) {
        return cardtypesList.contains(cardType);
    }
}
