package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.objects.MagicObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GainLifeEvent extends Event {

    private int amount;

    public GainLifeEvent(MagicObject source, int amount) {
        super(source);
        this.amount = amount;
    }
}
