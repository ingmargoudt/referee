package io.github.ingmargoudt.referee.players;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

public class Player extends BaseObject {

    private final String name;
    private Library library;
    private Hand hand = new Hand();
    private Game gameReference;
    private Manapool manapool;
    @Getter
    private int life;

    public Player(String name, Game game, Library library) {
        super();
        this.name = name;
        gameReference  = game;
        this.library = library;
        manapool = new Manapool();
    }

    public void shuffle() {
        EventBus.report(name +" shuffles");
        library.shuffle();
    }

    public void drawCard() {
        Optional<Card> drawnCard = library.drawCard();

        if (drawnCard.isPresent()) {
            EventBus.report(name + " draws a card");
            hand.addCard(drawnCard.get());
        } else {

        }
    }

    public void setLife(int startingLife) {
        EventBus.report(name + "'s life is set to "+startingLife);

    }
}
