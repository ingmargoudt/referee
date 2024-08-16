package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TargetAny extends Target {

    Targetable theTarget;

    @Override
    public List<Targetable> validTargets(Game game) {
        return Stream.concat(game.getPlayers().stream().map(Targetable.class::cast).filter(p -> filter.evaluate(p, game, source)),
                game.getBattlefield().getAll().stream().map(Targetable.class::cast)).collect(Collectors.toList());
    }

    @Override
    public Optional<Targetable> resolve(Game game) {
        if (theTarget instanceof Player) {
            return Optional.of(theTarget);
        }

        return game.getBattlefield().getAll().stream().filter(permanent -> permanent.getId().equals(theTarget.getId())).map(Targetable.class::cast).findFirst();
    }

    @Override
    public void choose(Stackable source, Game game, TargetEffect oneShotEffect) {
        Player player = source.getController();
        this.theTarget = player.chooseTarget(validTargets(game));
        EventBus.report(player.getName() + " chooses " + theTarget.getClass().getSimpleName() + " for " + source.getName() + "'s " + oneShotEffect.toString());

    }

    @Override
    public String getRule() {
        return "any target";
    }


}
