package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

import java.util.List;
import java.util.UUID;

public class Spell extends MagicObject implements Stackable{

    Card card;

    public Spell(Card card){
        super(card.getName());
        this.card = card;
    }

    public void resolve(Game game){
        if(card.isPermanent()){
            game.moveToBattlefield(card);
        }
        else{
            card.getSpellEffects().forEach(effect -> effect.apply(this, game));
        }
        
    }

    @Override
    public UUID getController() {
        return card.getController();
    }

    @Override
    public MagicObject getSource() {
        return card;
    }

    @Override
    public String getName(){
        return card.getName();
    }

    @Override
    public boolean hasTargets() {
        return card.getSpellEffects().stream().anyMatch(OneShotEffect::hasTargets);
    }

    @Override
    public List<OneShotEffect> getEffects() {
        return card.getSpellEffects();
    }
}
