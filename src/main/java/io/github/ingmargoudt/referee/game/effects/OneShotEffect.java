package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.targets.Target;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class OneShotEffect extends Effect {

    private final UUID id;
    @Getter
    protected List<Target> targets = new ArrayList<>();

    @Getter
    protected Event reflectedSource;


    protected OneShotEffect() {
        id = UUID.randomUUID();
        reflectedSource = null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract void apply(MagicObject object, Game game);

    public void choose(Stackable stackable, Game game) {
        targets.forEach(target -> target.choose(stackable, game, (TargetEffect) this));
    }

    public boolean hasTargets() {
        return !targets.isEmpty();
    }

    public boolean hasValidTargets(Game game, MagicObject object) {

        return targets.stream().noneMatch(target -> target.from(object).validTargets(game).isEmpty());
    }

    public void setReflectedSource(Event resultOfEvent){
        this.reflectedSource = resultOfEvent;
    }
}
