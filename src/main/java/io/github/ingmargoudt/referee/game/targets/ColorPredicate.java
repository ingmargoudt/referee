package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.Color;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public class ColorPredicate implements Predicate {
    private Color color;

    public ColorPredicate(Color color) {
        this.color = color;
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        if (target instanceof Spell spell) {
            return spell.getColor().contains(color);
        }
        if (target instanceof Permanent permanent) {
            return permanent.getColor().contains(color);
        }
        return false;
    }

    @Override
    public String getRule() {
        return color.name();
    }
}
