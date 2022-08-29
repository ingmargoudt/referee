package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AtTheBeginningOfStepEvent extends Event {

    private Step step;
    private Player controller;

    public AtTheBeginningOfStepEvent(Step step, Player thePlayer) {
        this.step = step;
        this.controller = thePlayer;
    }
}
