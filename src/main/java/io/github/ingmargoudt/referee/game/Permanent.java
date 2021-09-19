package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.abilities.Abilities;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.players.Player;

import java.util.UUID;

public class Permanent extends MagicObject{

    Card base;

    //Card current;

    /*
    110.5. A permanent’s status is its physical state. There are four status categories, each of which has two
possible values: tapped/untapped, flipped/unflipped, face up/face down, and phased in/phased out.
Each permanent always has one of these values for each of these categories.
     */
    boolean isTapped;
    boolean isFlipped;
    boolean isFacedown;
    boolean isPhasedOut;


    /*
    110.2. A permanent’s owner is the same as the owner of the card that represents it (unless it’s a token;
see rule 111.2). A permanent’s controller is, by default, the player under whose control it entered
the battlefield. Every permanent has a controller.
     */
    public Permanent(Card card){
        super(card.getName());
        this.base = card;
        this.controller = base.getOwner();
        reset();
    }
    public void reset(){

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

    }

    public UUID getOwner(){
        return base.getOwner();
    }

    public void tap(){
        isTapped = true;
    }

    public void untap(){
        isTapped = false;
    }

    public void flip(){
        isFlipped = true;
    }

    public void unflip(){
        isFlipped = false;
    }

    public void faseUp(){
        isFacedown = false;
    }

    public void faseDown(){
        isFacedown = true;
    }

    public void phaseIn(){
        isPhasedOut = false;
    }

    public void phaseOut(){
        isPhasedOut = true;
    }




    public boolean isControlledBy(Player thePlayer){
        return getController().equals(thePlayer.getId());
    }

    public UUID getId() {
        return base.id;
    }

    public boolean hasSubType(SubType subType) {
        return getSubTypes().has(subType);
    }


    public void destroy(Game game){
        game.moveToGraveyard(base);
    }


}
