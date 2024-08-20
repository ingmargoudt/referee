package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public interface Damageable {


    void receiveDamage(Game game, MagicObject source, int amount);

    String getName();
}
