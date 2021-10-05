package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.List;
import java.util.UUID;
public interface Stackable {

    void resolve(Game game);
    UUID getController();

    MagicObject getSource();

    String getName();

    boolean hasTargets();

    List<OneShotEffect> getEffects();
}
