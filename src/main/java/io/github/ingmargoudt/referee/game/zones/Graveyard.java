package io.github.ingmargoudt.referee.game.zones;

import io.github.ingmargoudt.referee.game.Card;

import java.util.ArrayList;
import java.util.*;
public class Graveyard {

    List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }
}
