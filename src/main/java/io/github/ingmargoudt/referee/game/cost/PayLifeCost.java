package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class PayLifeCost implements Cost {

    private final int amount;

    public PayLifeCost(int amount) {
        this.amount = amount;
    }


    @Override
    public void pay(MagicObject source, Game game) {
        source.getController().loseLife(amount);

    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
        return source.getController().getLife() >= amount;
    }

    @Override
    public String getRule() {
        return "pay "+amount + " life";
    }
}
