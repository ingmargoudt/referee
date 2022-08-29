package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

public class DrawCardEffect extends OneShotEffect{
    @Override
    public void apply(MagicObject object, Game game) {
        object.getController().drawCard();
    }

    @Override
    public String getRule() {
        return "Draw a card";
    }
}
