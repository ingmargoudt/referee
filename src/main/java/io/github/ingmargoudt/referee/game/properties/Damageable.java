package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

public interface Damageable {


    void damage(Player controller, MagicObject source, int amount);

    String getName();
}
