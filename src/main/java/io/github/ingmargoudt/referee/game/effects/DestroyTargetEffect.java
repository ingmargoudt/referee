package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Destroyable;
import io.github.ingmargoudt.referee.game.targets.Target;
import io.github.ingmargoudt.referee.players.Player;

public class DestroyTargetEffect extends OneShotEffect implements TargetEffect {


    public DestroyTargetEffect(Target target) {
        this.targets.add(target);
    }

    @Override
    public void apply(MagicObject object, Game game) {
        Player player = object.getController();
        targets.get(0).resolve(game)
                .map(Destroyable.class::cast)
                .ifPresent(theTarget -> {
                    theTarget.destroy(game, player, object);
                });
    }

    @Override
    public String getRule() {
        return null;
    }
}
