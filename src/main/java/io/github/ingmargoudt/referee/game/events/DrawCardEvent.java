package io.github.ingmargoudt.referee.game.events;

import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

public class DrawCardEvent extends Event{

    @Getter
    private Player player;

    public DrawCardEvent(Player player){
        this.player = player;
    }

    @Override
    public boolean isControlledBy(Player thePlayer){
        return player == thePlayer;
    }
}
