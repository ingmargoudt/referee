package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Spell;
import io.github.ingmargoudt.referee.game.abilities.Ability;

public class YouGainLifeEffect extends OneShotEffect{

    private int amount;

    public YouGainLifeEffect(int amount){
        this.amount = amount;
    }

    @Override
    public void apply(Spell source, Game game) {
        game.getPlayer(source.getController()).ifPresent(controller -> controller.gainLife(amount));
    }
}
