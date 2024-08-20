package io.github.ingmargoudt.referee.players;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Manapool;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.abilities.ActivatedManaAbility;
import io.github.ingmargoudt.referee.game.abilities.Lifelink;
import io.github.ingmargoudt.referee.game.events.GainLifeEvent;
import io.github.ingmargoudt.referee.game.objects.BaseObject;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.game.zones.Graveyard;
import io.github.ingmargoudt.referee.game.zones.Hand;
import io.github.ingmargoudt.referee.game.zones.Library;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class Player extends BaseObject implements Targetable, Damageable {

    private final String name;
    @Getter
    protected Hand hand = new Hand();
    protected Game gameReference;
    private final Library library;
    @Getter
    private final Graveyard graveyard;
    @Getter
    private final Manapool manapool;
    @Getter
    private int life;

    public Player(String name, Game game, Library library) {
        super();
        this.name = name;
        gameReference = game;
        this.library = library;
        this.library.setOwner(this);
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

    public void activateAbility(ActivatedAbility activatedAbility, MagicObject source){
        activatedAbility.payCosts(source, gameReference);
        if (activatedAbility instanceof ActivatedManaAbility) {
            activatedAbility.resolve(source, gameReference);
        } else {
            gameReference.putOnStack(activatedAbility, source);
        }
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
        EventBus.report(getName() + " gains " + gainLifeEvent.getAmount() + " life from "+source.getName());
        life += gainLifeEvent.getAmount();
    }

    public void playLand(Card card) {
        hand.remove(card);
        gameReference.moveToBattlefield(card);
    }

    @Override
    public void damage(Game game, MagicObject source, int amount) {
        EventBus.report(source.getController().getName() + "'s " + source.getName() + " deals " + amount + " damage to " + getName());
        life -= amount;
        if(source.hasAbility(Lifelink.class)){
            source.getController().gainLife(game, amount, source);
        }

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

    public Player getController() {
        throw new IllegalArgumentException();
    }

    public void discard(Card card) {
        EventBus.report(getName() + " discards "+card.getName());
        hand.remove(card);
        putCardInGraveyard(card);
    }

    public void declareAttacker(Card card) {
        gameReference.getBattlefield().getAll().stream().filter(p->p.getBase().getId().equals(card.getId())).findFirst().ifPresent(p->{
            EventBus.report(this.getName() + " declares "+card.getName() + " to attack");
            p.setDeclaredAsAttacker(true);
        });
    }
}
