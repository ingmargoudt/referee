package io.github.ingmargoudt.referee.game.abilities;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.effects.AddManaEffect;
import io.github.ingmargoudt.referee.game.effects.Effect;
import io.github.ingmargoudt.referee.game.effects.Effects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivatedManaAbility extends ActivatedAbility {
    public ActivatedManaAbility(List<Cost> costsList, Effects effects) {
        super(costsList,effects);
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        //Card card = game.getCard(source);
        //Player controller = game.getPlayer(card.getController());
        for (Effect effect : effects.get()) {
            if (effect instanceof AddManaEffect) {
                effect.apply(source, game);
            }
        }
    }
}
