package io.github.ingmargoudt.referee.game.zones;

import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.players.Player;

import java.util.*;


public class Library {

    private final LinkedList<Card> cards = new LinkedList<>();

    public Library(List<Card> cardList) {
        for (Card c : cardList) {
            cards.addFirst(c);
        }
    }

    public void setOwner(Player owner) {
        for (Card card : cards) {
            card.setOwner(owner);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Optional<Card> drawCard() {
        return Optional.ofNullable(cards.pop());
    }

    public void putOnTop(Card card) {
        cards.addFirst(card);
    }
}
