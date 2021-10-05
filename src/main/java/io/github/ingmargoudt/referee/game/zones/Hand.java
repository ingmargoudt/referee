package io.github.ingmargoudt.referee.game.zones;

import io.github.ingmargoudt.referee.game.objects.Card;

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

    public boolean remove(Card card) {
        return cards.removeIf(c -> c.getId().equals(card.getId()));
    }
}
