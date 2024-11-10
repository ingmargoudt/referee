package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.zones.Hand;

public class SacrificeSourceCost implements Cost {
    @Override
    public void pay(MagicObject source, Game game) {
        if (source instanceof Permanent permanent) {
            source.getController().putCardInGraveyard(permanent.getBase());
        }

    }

    @Override
    public boolean canPay(MagicObject source, Game game) {
        return true;

    }

    @Override
    public String getRule() {
        return "Sacrifice {this}";
    }
}
