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
            if(source instanceof Card) {
                source.getController().discard((Card)source);
            }

    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
            var hand = source.getController().getHand();
            return hand.getCards().contains(source);

    }

    @Override
    public String getRule() {
        return "Discard this card";
    }
}
