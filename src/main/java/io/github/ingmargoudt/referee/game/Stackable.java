package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.effects.OneShotEffect;

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
