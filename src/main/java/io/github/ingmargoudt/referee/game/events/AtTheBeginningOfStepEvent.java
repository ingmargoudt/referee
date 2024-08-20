package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;


@Getter
public class AtTheBeginningOfStepEvent extends Event {

    private final Step step;
    private final Player activePlayer;

    public AtTheBeginningOfStepEvent(Step step, Player thePlayer) {
        this.step = step;
        this.activePlayer = thePlayer;
        EventBus.report("Starting "+step);
    }
}
