package io.github.ingmargoudt.referee.players;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Manapool;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.abilities.StackAbility;
import io.github.ingmargoudt.referee.game.events.GainLifeEvent;
import io.github.ingmargoudt.referee.game.objects.*;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.game.zones.Graveyard;
import io.github.ingmargoudt.referee.game.zones.Hand;
import io.github.ingmargoudt.referee.game.zones.Library;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Player extends BaseObject implements Targetable, Damageable {

    private final String name;
    protected Hand hand = new Hand();
    protected Game gameReference;
    private Library library;
    @Getter
    private Graveyard graveyard;
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
        graveyard = new Graveyard();
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

    public void setLife(int lifeTotal) {
        EventBus.report(name + "'s life is set to " + lifeTotal);
        this.life = lifeTotal;

    }

    public void mulligan() {

    }

    public void castSpell(Card card) {
        if (hand.remove(card)) {
            EventBus.report(getName() + " casts " + card.getName());
            gameReference.putOnStack(new Spell(card));
        } else {
            EventBus.report(card.getName() + " not found in " + getName() + "'s hand");
        }

    }

    public void activateAbility(ActivatedAbility activatedAbility, Permanent source){
        gameReference.putOnStack(activatedAbility, source);
    }


    public void putCardOnTop(Card card) {
        library.putOnTop(card);
    }

    public String getName() {
        return name;
    }

    public void doAction() {
        passPriority();
    }

    protected void passPriority() {
        gameReference.passPriority();
    }

    public void putCardInGraveyard(Card card) {
        graveyard.add(card);
    }

    public void gainLife(Game game, int amount, MagicObject source) {
        GainLifeEvent gainLifeEvent = new GainLifeEvent(source, amount);
        game.raiseEvent(gainLifeEvent);
        EventBus.report(getName() + " gains " + gainLifeEvent.getAmount() + " life");
        life += gainLifeEvent.getAmount();
    }

    public void playLand(Card card) {
        hand.remove(card);
        gameReference.moveToBattlefield(card);
    }

    @Override
    public void damage(Player controller, MagicObject source, int amount) {
        EventBus.report(controller.getName() + "'s " + source.getName() + " deals " + amount + " damage to " + getName());
        life -= amount;

    }

    @Override
    public String toString() {
        return name;
    }

    public Targetable chooseTarget(List<Targetable> validTargets) {
        throw new NotImplementedException();
    }

    public String choosesOption(List<String> options) {
        return options.get(0);
    }

    public void loseLife(int amount) {
        life -= amount;
    }

    public UUID getController() {
        throw new IllegalArgumentException();
    }
}
