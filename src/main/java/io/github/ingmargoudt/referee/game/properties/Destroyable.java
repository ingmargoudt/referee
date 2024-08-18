package io.github.ingmargoudt.referee.game.properties;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

public interface Destroyable {


    void destroy(Game game, MagicObject source);
}
