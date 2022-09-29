package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.targets.ColorPredicate;

public enum Color {
    WHITE,
    BLUE,
    BLACK,
    RED,
    GREEN;

    public ColorPredicate getPredicate(){
        return new ColorPredicate(this);
    }
}
