package io.github.ingmargoudt.referee.game.zones;

import io.github.ingmargoudt.referee.game.objects.Card;

import java.util.ArrayList;
import java.util.List;

public class Graveyard {

    List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }
}
