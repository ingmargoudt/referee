package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class YouGainLifeEffect extends OneShotEffect {

    private final int amount;

    public YouGainLifeEffect(int amount) {
        this.amount = amount;
    }

    @Override
    public void apply(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(controller -> controller.gainLife(game, amount, source));
    }

    @Override
    public String getRule() {
        return "You gain " + amount + " life";
    }
}
