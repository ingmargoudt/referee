package io.github.ingmargoudt.referee.game;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

public class Library {

    private LinkedList<Card> cards = new LinkedList<>();

    public Library() {

    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Optional<Card> drawCard() {
        return Optional.ofNullable(cards.peekFirst());
    }
}
