package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Card extends MagicObject implements Targetable{
    
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

    public boolean canBePlayed(Game game){
        return getSpellEffects().stream().filter(oneShotEffect -> oneShotEffect instanceof TargetEffect).map(oneShotEffect -> (TargetEffect) oneShotEffect).allMatch(target-> target.hasValidTargets(game));
    }

}
