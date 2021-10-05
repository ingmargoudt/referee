package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TargetAny implements Target {

    Targetable theTarget;

    @Override
    public List<Targetable> validTargets(Game game) {
        return Stream.concat(game.getPlayers().stream().map(Targetable.class::cast),
                game.getBattlefield().getAll().stream().map(Targetable.class::cast)).collect(Collectors.toList());
    }

    @Override
    public Optional<Targetable> resolve(Game game) {
        Optional<Player> targettedPlayer = game.getPlayer(theTarget.getId());
        if (targettedPlayer.isPresent()) {
            return Optional.of(targettedPlayer.get());
        }
        Optional<Permanent> targetedPermanent = game.getBattlefield().getAll().stream().filter(permanent -> permanent.getId().equals(theTarget.getId())).findFirst();
        if (targetedPermanent.isPresent()) {
            return Optional.of(targetedPermanent.get());
        }
        return Optional.empty();
    }

    @Override
    public void choose(Stackable source, Game game, OneShotEffect oneShotEffect) {
        game.getPlayer(source.getController()).ifPresent(player -> {
            this.theTarget = player.chooseTarget(validTargets(game));
            EventBus.report(player.getName() + " chooses " + theTarget.getClass().getSimpleName() + " for " + source.getName() +"'s "+ oneShotEffect.toString());
        });
    }


}
