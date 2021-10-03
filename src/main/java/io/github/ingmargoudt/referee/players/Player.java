package io.github.ingmargoudt.referee.players;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.framework.Question;
import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.game.zones.Graveyard;
import io.github.ingmargoudt.referee.game.zones.Hand;
import io.github.ingmargoudt.referee.game.zones.Library;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class Player extends BaseObject implements Targetable {

    private final String name;
    private Library library;
    private Graveyard graveyard;
    protected Hand hand = new Hand();
    protected Game gameReference;
    @Getter
    private Manapool manapool;
    @Getter
    private int life;

    public Player(String name, Game game, Library library) {
        super();
        this.name = name;
        gameReference = game;
        this.library = library;
        this.library.setOwner(this.id);
        manapool = new Manapool();
    }

    public void shuffle() {
        EventBus.report(name + " shuffles");
        library.shuffle();
    }

    public void drawCard(int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard();
        }
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
        EventBus.report(name + "'s life is set to " + startingLife);
        this.life = startingLife;

    }

    public void mulligan() {
        int mulligans = 0;

        while (Question.askYesNo(getId(), "Would you like to mulligan down to "+(7-1-mulligans)) && (7-mulligans) > 0) {
            mulligans++;
            for (Card card : hand.getCards()) {
                putCardOnTop(card);
            }
            shuffle();
            drawCard(7 - mulligans);
        }
        EventBus.report(name + " keeps their hand of "+hand.getSize());
    }

    public void castSpell(Card card){
        if(hand.remove(card)) {
            EventBus.report(getName() + " casts " + card.getName());
            gameReference.putOnStack(new Spell(card));
        }
        else{
            EventBus.report(card.getName() + " not found in "+getName()+"'s hand");
        }

    }


    public void putCardOnTop(Card card) {
        library.putOnTop(card);
    }

    public String getName(){
        return name;
    }

    public void doAction(){
        passPriority();
    }

    protected void passPriority() {
        gameReference.passPriority();
    }

    public void putCardInGraveyard(Card card) {
        graveyard.add(card);
    }

    public void gainLife(int amount) {
        EventBus.report(getName() + " gains "+amount + " life");
        life+=amount;
    }

    public void playLand(Card card) {
        hand.remove(card);
        gameReference.moveToBattlefield(card);
    }

    @Override
    public void damage(int amount) {
        life -= amount;

    }

    public Targetable chooseTarget(List<Targetable> validTargets) {
        throw new NotImplementedException();
    }
}
