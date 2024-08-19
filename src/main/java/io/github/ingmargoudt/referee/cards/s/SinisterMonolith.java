package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.abilities.AtTheBeginningOfYourStepAbility;
import io.github.ingmargoudt.referee.game.abilities.TriggeredAbility;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class SinisterMonolith extends Card {
    public SinisterMonolith() {
        super("Sinister Monolith");
        cardtypes.add(CardType.ARTIFACT);
        abilities.add(new AtTheBeginningOfYourStepAbility(Step.BEGINNING_OF_COMBAT, new SinisterMonolithEffect()));
    }


}

class SinisterMonolithEffect extends OneShotEffect {

    @Override
    public void apply(MagicObject object, Game game) {
        game.getPlayers().forEach(player -> {
            if(!player.equals(object.getController())){
                player.loseLife(1);
            }
        });
        object.getController().gainLife(game, 1, object);
    }

    @Override
    public String getRule() {
        return ", each opponent loses 1 life and you gain 1 life.";
    }
}
