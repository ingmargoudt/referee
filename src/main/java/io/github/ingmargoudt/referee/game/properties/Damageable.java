package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public interface Damageable {


    void damage(Game game, MagicObject source, int amount);

    String getName();
}
