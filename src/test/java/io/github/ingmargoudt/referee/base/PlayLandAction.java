package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.Phase;
import lombok.Getter;

public class PlayLandAction extends TestPlayerAction {

    @Getter
    private Card card;


    public PlayLandAction(int turn, Phase phase, Card card){
        super(turn, phase);
        this.card = card;
    }

    @Override
    public void execute(TestPlayer player) {
        player.playLand(card);
    }
}
