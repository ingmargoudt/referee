package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;

public abstract class PlayerAction {

    int turn;
    Phase phase;

    public PlayerAction(int turn , Phase phase){
        this.turn  = turn;
        this.phase = phase;
    }

   public abstract void execute(TestPlayer player);
}
