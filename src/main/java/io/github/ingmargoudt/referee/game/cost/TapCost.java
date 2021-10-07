package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;

public class TapCost implements Cost {


    @Override
    public void pay(MagicObject source, Game game) {
        ((Permanent) source).tap();
    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
        if (source instanceof Permanent) {
            return !((Permanent) source).isTapped();
        }
        return false;
    }
}
