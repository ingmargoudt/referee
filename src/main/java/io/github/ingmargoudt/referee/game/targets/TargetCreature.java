package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TargetCreature extends Target {

    Targetable theTarget;

    public TargetCreature() {

    }

    public TargetCreature(Filter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public List<Targetable> validTargets(Game game) {
        return game.getBattlefield().getAll().stream().filter(MagicObject::isCreature).map(Targetable.class::cast).filter(targetable -> filter.evaluate(targetable, game, source)).toList();
    }

    @Override
    public Optional<Targetable> resolve(Game game) {
        return game.getBattlefield().getAll().stream().filter(permanent -> permanent.getId().equals(theTarget.getId())).filter(it -> filter.evaluate(it, game, source)).map(Targetable.class::cast).findFirst();
    }

    @Override
    public void choose(Stackable source, Game game, TargetEffect oneShotEffect) {
        var player = source.getController();
        this.theTarget = player.chooseTarget(validTargets(game));
        EventBus.report(player.getName() + " chooses " + theTarget.getClass().getSimpleName() + " for " + source.getName() + "'s " + oneShotEffect.getClass().getSimpleName());
    }

    @Override
    public String getRule() {
        return "target " + filter.getRule() + "creature";
    }


}
