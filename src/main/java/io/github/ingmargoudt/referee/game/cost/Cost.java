package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public interface Cost {

    void pay(MagicObject source, Game game);
    boolean canPay(MagicObject source, Game game);
}
