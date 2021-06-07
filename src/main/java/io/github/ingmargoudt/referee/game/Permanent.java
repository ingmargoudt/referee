package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public class Permanent {

    Card base;
    Card current;

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
        this.base = card;
        reset();
    }
    public void reset(){
        this.current = base;
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


}
