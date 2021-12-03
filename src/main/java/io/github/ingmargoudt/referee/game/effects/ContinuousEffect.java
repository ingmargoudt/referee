package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Duration;
import io.github.ingmargoudt.referee.game.properties.DurationType;
import io.github.ingmargoudt.referee.game.targets.Filter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ContinuousEffect extends Effect {

    private final UUID id;
    @Getter
    private final Duration duration;
    @Getter
    private List<MagicObject> affectedObjects;
    @Getter
    @Setter
    private boolean affectedObjectsDetermined = false;

    Filter filter;

    protected ContinuousEffect() {
        id = UUID.randomUUID();
        filter = Filter.empty();
        duration = Duration.Continuous();
    }

    protected ContinuousEffect(DurationType durationType) {
        id = UUID.randomUUID();
        filter = Filter.empty();
        this.duration = new Duration(-1, durationType);
        affectedObjects = new ArrayList<>();
    }


    public void lockInObjects(Game game, MagicObject source){
        affectedObjectsDetermined = true;
    }


}
