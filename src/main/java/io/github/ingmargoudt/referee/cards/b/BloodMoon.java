package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.cards.m.Mountain;
import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;

public class BloodMoon extends Card {

    public BloodMoon(){
        super("Blood Moon");
        addAbility(new StaticAbility(new BloodMoonEffect()));
    }


}
class BloodMoonEffect extends ContinuousEffect {
    @Override
    public void apply(MagicObject source, Game game) {
        game.getBattlefield().getAll().forEach(permanent -> {
            if (permanent.isLand()) {
                if(!permanent.isBasic()){
                    permanent.getAbilities().clear();
                    permanent.getSubTypes().clear();
                    permanent.getSubTypes().add(SubType.Mountain);
                    permanent.getReplacementEffects().clear();
                }

            }
        });
    }
}