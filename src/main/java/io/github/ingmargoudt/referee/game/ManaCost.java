package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.cost.Cost;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class ManaCost implements Cost {

    private final String type;

    public ManaCost(String type){
        this.type = type;
    }

    @Override
    public void pay(MagicObject source, Game game) {

    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
        return true;
    }

    @Override
    public String getRule() {
        return null;
    }
}
