package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.objects.MagicObject;

public interface Damageable {


    void damage(MagicObject source, int amount);

    String getName();
}
