package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TargetSpell extends Target {

    Targetable theTarget;


    public TargetSpell(Filter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public List<Targetable> validTargets(Game game) {
        return game.getStack().show().stream().filter(Spell.class::isInstance).filter(spell -> filter.evaluate(spell, game, source)).collect(Collectors.toList());
    }

    @Override
    public Optional<Targetable> resolve(Game game) {
        return game.getStack().show().stream().filter(Spell.class::isInstance).filter(spell -> spell.getId().equals(theTarget.getId())).filter(spell -> filter.evaluate(spell, game, source)).findFirst();
    }

    @Override
    public void choose(Stackable source, Game game, OneShotEffect oneShotEffect) {
        game.getPlayer(source.getController()).ifPresent(player -> {
            this.theTarget = player.chooseTarget(validTargets(game));
            EventBus.report(player.getName() + " chooses " + theTarget.getClass().getSimpleName() + " for " + source.getName() + "'s " + oneShotEffect.toString());
        });
    }

    public String getRule() {
        return filter.getRule() + " spell";
    }


}
