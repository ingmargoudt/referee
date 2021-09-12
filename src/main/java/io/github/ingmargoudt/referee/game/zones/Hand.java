package io.github.ingmargoudt.referee.game.zones;

import io.github.ingmargoudt.referee.game.Card;

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

    public void remove(Card card) {
        cards.removeIf(c -> c.getId().equals(card.getId()));
    }
}