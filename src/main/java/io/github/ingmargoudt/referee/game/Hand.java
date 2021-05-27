package io.github.ingmargoudt.referee.game;

import java.util.HashSet;
import java.util.Set;

public class Hand {

    Set<Card> cards = new HashSet<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public Set<Card> getCards(){
        return cards;
    }


    public int getSize() {
        return cards.size();
    }
}
