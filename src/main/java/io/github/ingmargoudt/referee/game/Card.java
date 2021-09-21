package io.github.ingmargoudt.referee.game;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Card extends MagicObject{

    @Setter
    UUID owner;

    public Card(String name){
        super(name);
    }

    public void setOwner(UUID owner){
        if(this.owner == null){
            this.owner = owner;
        }
    }

    public boolean isPermanent() {
        return getCardtypes().isPermanent();
    }

    public Card copy(){
        return new Card(this);
    }

    private Card(Card card){
        super(card.getName());
        this.setController(card.getController());
        this.owner = card.getOwner();
        this.setPower(card.getPower());
        this.setToughness(card.getToughness());
        this.getAbilities().addAll(card.getAbilities());
        this.getCardtypes().addAll(card.getCardtypes());
        this.getSubTypes().addAll(card.getSubTypes());
        this.getSpellEffects().addAll(card.getSpellEffects());
    }

}
