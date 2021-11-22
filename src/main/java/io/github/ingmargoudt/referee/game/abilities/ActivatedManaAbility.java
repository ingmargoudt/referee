package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.List;

public class ActivatedManaAbility extends ActivatedAbility {
    public ActivatedManaAbility(Costs costsList, Effects<OneShotEffect> effects) {
        super(costsList, effects);
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        //Card card = game.getCard(source);
        //Player controller = game.getPlayer(card.getController());
        effects.apply(source, game);
//        for (OneShotEffect effect : effects.) {
//            if (effect instanceof AddManaEffect) {
//                effect.apply(source, game);
//            }
//        }
    }
}
