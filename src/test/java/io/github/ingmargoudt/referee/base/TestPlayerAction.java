package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.Step;

public abstract class TestPlayerAction {

    int turn;
    Phase phase;
    Step step;

    public TestPlayerAction(int turn, Phase phase) {
        this.turn = turn;
        this.phase = phase;
    }

    public TestPlayerAction(int turn, Step step) {
        this.turn = turn;
        this.step = step;
    }

    public abstract void execute(TestPlayer player);

}
