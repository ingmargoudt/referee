package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

import java.util.UUID;

public interface Stackable {

    void resolve(Game game);

    Player getController();


    UUID getId();

    MagicObject getSource();

    String getName();

    boolean hasTargets();

    Effects<OneShotEffect> getEffects();
}
