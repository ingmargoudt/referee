package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TargetAny implements Target {

    Targetable theTarget;

    public boolean isValid(MagicObject source, Game game) {
        return game.getBattlefield().getAll().stream().anyMatch(permanent -> permanent.getId().equals(theTarget.getId()));
    }

    @Override
    public List<Targetable> validTargets() {
        return new ArrayList<>();
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
            this.theTarget = player.chooseTarget(validTargets());
            EventBus.report(player.getName() + " chooses " + theTarget.toString() + " for " + source.getName() +"'s "+ oneShotEffect.toString());
        });
    }


}
