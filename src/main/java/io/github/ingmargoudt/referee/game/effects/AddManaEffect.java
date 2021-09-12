package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.players.Player;

public class AddManaEffect extends Effect {

    private final ManaType manaType;

    public AddManaEffect(ManaType manaType){
        this.manaType = manaType;
    }
    @Override
    public void apply(Ability source, Game game) {
        game.getPlayer(source.getController()).ifPresent(controller -> {
            controller.getManapool().add(manaType);
        });
    }
}
