package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class PayLifeCost implements Cost {

    private final int amount;

    public PayLifeCost(int amount){
        this.amount=amount;
    }


    @Override
    public void pay(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(player -> player.loseLife(amount));

    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
        return game.getPlayer(source.getController()).map(player -> player.getLife() >= amount).orElse(false);
    }
}
