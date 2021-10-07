package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.properties.Damageable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

import java.util.UUID;

public class Permanent extends MagicObject implements Targetable, Damageable {

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


    /*
    110.2. A permanent’s owner is the same as the owner of the card that represents it (unless it’s a token;
see rule 111.2). A permanent’s controller is, by default, the player under whose control it entered
the battlefield. Every permanent has a controller.
     */
    public Permanent(Card card) {
        super(card.getName());
        this.base = card;
        this.controller = base.getOwner();
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
        this.getSpellEffects().clear();
        this.getSpellEffects().addAll(base.getSpellEffects());
        this.getReplacementEffects().clear();
        this.getReplacementEffects().addAll(base.getReplacementEffects());

    }

    public UUID getOwner() {
        return base.getOwner();
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
        return getController().equals(thePlayer.getId());
    }

    @Override
    public UUID getId() {
        return base.id;
    }

    public boolean hasSubType(SubType subType) {
        return getSubTypes().has(subType);
    }


    public void destroy(Game game) {
        game.moveToGraveyard(base);
    }


    @Override
    public void damage(int amount) {
        damageReceived += amount;
    }

    public int getReceivedDamage() {
        return damageReceived;
    }
}
