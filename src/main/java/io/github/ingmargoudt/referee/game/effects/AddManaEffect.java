package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class AddManaEffect extends OneShotEffect {

    private final ManaType manaType;

    public AddManaEffect(ManaType manaType) {
        this.manaType = manaType;
    }

    @Override
    public void apply(MagicObject source, Game game) {
        game.getPlayer(source.getController()).ifPresent(controller -> {
            controller.getManapool().add(manaType);
        });
    }

    @Override
    public String getRule() {
        return "Add " + manaType.getLetter() + " to your manapool";
    }
}
