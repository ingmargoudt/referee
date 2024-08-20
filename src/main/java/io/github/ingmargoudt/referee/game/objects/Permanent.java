package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.Indestructible;
import io.github.ingmargoudt.referee.game.abilities.Lifelink;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.properties.Destroyable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Permanent extends MagicObject implements Targetable, Damageable, Destroyable {

    @Getter
    Card base;

    /*
    110.5. A permanent’s status is its physical state. There are four status categories, each of which has two
possible values: tapped/untapped, flipped/unflipped, face up/face down, and phased in/phased out.
Each permanent always has one of these values for each of these categories.
     */
    @Getter
    boolean tapped;
    boolean isFlipped;
    boolean isFacedown;
    boolean isPhasedOut;

    int damageReceived = 0;

    @Getter
    @Setter
    boolean declaredAsAttacker;
    @Getter
    List<Permanent> blockers;


    /*
    110.2. A permanent’s owner is the same as the owner of the card that represents it (unless it’s a token;
see rule 111.2). A permanent’s controller is, by default, the player under whose control it entered
the battlefield. Every permanent has a controller.
     */
    public Permanent(Card card) {
        super(card.getName());
        this.base = card;
        this.controller = base.getOwner();
        this.blockers = new ArrayList<>();
        reset();
    }

    public void reset() {

        this.setPower(base.getPower());
        this.setToughness(base.getToughness());
        this.getAbilities().clear();
        this.getAbilities().addAll(base.getAbilities());
        this.getCardtypes().clear();
        this.getCardtypes().addAll(base.getCardtypes());
        this.getSubTypes().clear();
        this.getSubTypes().addAll(base.getSubTypes());
        this.getSuperTypes().clear();
        this.getSuperTypes().addAll(base.getSuperTypes());
        this.spellEffects = new Effects<>(base.getSpellEffects());
        this.getReplacementEffects().clear();
        this.getReplacementEffects().addAll(base.getReplacementEffects());
        this.manaCost = base.getManaCost();
    }

    public void tap() {
        tapped = true;
    }

    public void untap() {
        tapped = false;
    }

    public void flip() {
        isFlipped = true;
    }

    public void unflip() {
        isFlipped = false;
    }

    public void faseUp() {
        isFacedown = false;
    }

    public void faseDown() {
        isFacedown = true;
    }

    public void phaseIn() {
        isPhasedOut = false;
    }

    public void phaseOut() {
        isPhasedOut = true;
    }


    public boolean isControlledBy(Player thePlayer) {
        return super.isControlledBy(thePlayer);
    }

    @Override
    public UUID getId() {
        return base.id;
    }

    public boolean hasSubType(SubType subType) {
        return getSubTypes().has(subType);
    }


    public void destroy(Game game) {
        EventBus.report(this.getName() + " dies");

        game.moveToGraveyard(base);
    }


    @Override
    public void damage(Game game, MagicObject source, int amount) {

        EventBus.report(source.getController().getName() + "'s " + source.getName() + " deals " + amount + " damage to " + getName());
        damageReceived += amount;
        if(source.hasAbility(Lifelink.class)){
            source.getController().gainLife(game, amount, source);
        }
    }

    public int getReceivedDamage() {
        return damageReceived;
    }

    public boolean wouldDieFromDamage(){
        return getReceivedDamage() >= getToughness() && isCreature() && !hasAbility(Indestructible.class);
    }

    public boolean hasZeroToughness(){
        return toughness <=0 && isCreature() && !hasAbility(Indestructible.class);
    }

    @Override
    public void destroy(Game game, MagicObject source) {
        EventBus.report(this.getName() + " dies from "+source.getName());

        game.moveToGraveyard(base);
    }
}
