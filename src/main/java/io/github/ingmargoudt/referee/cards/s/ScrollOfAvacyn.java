package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.ManaCost;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.cost.Costs;
import io.github.ingmargoudt.referee.game.cost.SacrificeSourceCost;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

public class ScrollOfAvacyn extends Card {
    public ScrollOfAvacyn() {
        super("Scroll of Avacyn");
        cardtypes.add(CardType.ARTIFACT);
        addAbility(new ActivatedAbility(Costs.of(new ManaCost("{1}"), new SacrificeSourceCost()), new ScrollOfAvacynEffect()));
    }
}
class ScrollOfAvacynEffect extends OneShotEffect {

    @Override
    public void apply(MagicObject source, Game game) {
        var controller = source.getController();
        controller.drawCard();
        if(game.getBattlefield().getAll().stream().anyMatch(p-> p.isControlledBy(source.getController()) && p.hasSubType(SubType.ANGEL))){
            controller.gainLife(game, 5, source);
        }
    }

    @Override
    public String getRule() {
        return "Draw a card. If you control an Angel, you gain 5 life.";
    }
}