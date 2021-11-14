package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class BloodMoon extends Card {

    public BloodMoon() {
        super("Blood Moon");
        addAbility(new StaticAbility(new BloodMoonEffect()));
    }


}

class BloodMoonEffect extends ContinuousEffect {
    @Override
    public void apply(MagicObject source, Game game) {
        game.getBattlefield().getAll().forEach(permanent -> {
            if (permanent.isLand() && !permanent.isBasic()) {
                permanent.getAbilities().clear();
                permanent.getSubTypes().clear();
                permanent.getSubTypes().add(SubType.MOUNTAIN);
                permanent.getReplacementEffects().clear();
            }
        });
    }

    public String getRule(){
        return "Nonbasic lands are Mountains";
    }
}