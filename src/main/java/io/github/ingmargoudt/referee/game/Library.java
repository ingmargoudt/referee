package io.github.ingmargoudt.referee.game;

import java.util.*;


public class Library {

    private LinkedList<Card> cards = new LinkedList<>();

    public Library(List<Card> cardList) {
        for (Card c : cardList) {
            cards.addFirst(c);
        }
    }

    public void setOwner(UUID owner){
        for(Card card : cards){
            card.owner = owner;
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Optional<Card> drawCard() {
        return Optional.ofNullable(cards.pop());
    }
}
