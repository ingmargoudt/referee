package io.github.ingmargoudt.referee.cards.p;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.abilities.triggered.AtTheBeginningOfYourStepAbility;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

public class PhyrexianArena extends Card {
    public PhyrexianArena() {
        super("Phyrexian Arena");
        getCardtypes().add(CardType.ENCHANTMENT);
        abilities.add(new AtTheBeginningOfYourStepAbility(Step.UPKEEP, new PhyrexianArenaEffect()));
    }


}

class PhyrexianArenaEffect extends OneShotEffect {
    @Override
    public void apply(MagicObject object, Game game) {
        var controller = object.getController();
        controller.drawCard();
        controller.loseLife(1);

    }

    @Override
    public String getRule() {
        return "you draw a card and you lose 1 life.";
    }
}