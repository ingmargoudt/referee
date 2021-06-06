package io.github.ingmargoudt.referee.game;

import java.util.UUID;

public class Permanent {

    Card card;
    boolean isTapped;

    /*
    110.2. A permanent’s owner is the same as the owner of the card that represents it (unless it’s a token;
see rule 111.2). A permanent’s controller is, by default, the player under whose control it entered
the battlefield. Every permanent has a controller.
     */
    public Permanent(Card card){
        this.card = card;
    }

    public UUID getOwner(){
        return card.getOwner();
    }

    public void tap(){
        isTapped = true;
    }

    public void untap(){
        isTapped = false;
    }


}
