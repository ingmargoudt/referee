package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Card extends MagicObject implements Targetable {

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
        return getSpellEffects().stream().filter(TargetEffect.class::isInstance).map(TargetEffect.class::cast).allMatch(target-> target.hasValidTargets(game));
    }

}
