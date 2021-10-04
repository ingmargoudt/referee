package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.ManaType;

public class AddManaEffect extends OneShotEffect {

    private final ManaType manaType;

    public AddManaEffect(ManaType manaType){
        this.manaType = manaType;
    }
    @Override
    public void apply(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(controller -> {
            controller.getManapool().add(manaType);
        });
    }
}
