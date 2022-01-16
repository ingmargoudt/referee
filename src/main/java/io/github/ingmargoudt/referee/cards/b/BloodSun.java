package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.EntersTheBattlefieldAbility;
import io.github.ingmargoudt.referee.game.abilities.StaticAbility;
import io.github.ingmargoudt.referee.game.effects.ContinuousEffect;
import io.github.ingmargoudt.referee.game.effects.DrawCardEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class BloodSun extends Card {

    public BloodSun() {
        super("Blood Sun");
        cardtypes.add(CardType.ENCHANTMENT);
        addAbility(new EntersTheBattlefieldAbility(new DrawCardEffect()));
        addAbility(new StaticAbility(new BloodSunEffect()));
    }


}

class BloodSunEffect extends ContinuousEffect {
    @Override
    public void apply(MagicObject source, Game game) {
        game.getBattlefield().getAll().forEach(permanent -> {
            if (permanent.isLand()) {
                permanent.getAbilities().clear();
                permanent.getReplacementEffects().clear();
            }
        });
    }

    public String getRule() {
        return "All lands lose all abilities except mana abilities.";
    }
}