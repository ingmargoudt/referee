package io.github.ingmargoudt.referee.game.abilities.mana;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class ActivatedManaAbility extends ActivatedAbility {
    public ActivatedManaAbility(Costs costsList, Effects<OneShotEffect> effects) {
        super(costsList, effects);
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        effects.apply(source, game);
    }
}
