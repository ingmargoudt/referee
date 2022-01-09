package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.zones.Hand;
import io.github.ingmargoudt.referee.players.Player;

import java.util.Optional;

public class DiscardThisCardCost implements Cost {
    @Override
    public void pay(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(controller ->
        {
            if(source instanceof Card) {
                controller.discard((Card)source);
            }
        });
    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
        Optional<Player> controller = game.getPlayer(source.getController());
        if(controller.isPresent() && source instanceof Card){
            Hand hand = controller.get().getHand();
            return hand.getCards().contains(source);
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Discard this card";
    }
}
