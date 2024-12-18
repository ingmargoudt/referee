package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.targets.Filter;

public class BoostThisCreatureForEachEffect extends ContinuousEffect {

    final int power;
    final int toughness;

    public BoostThisCreatureForEachEffect(int power, int toughness, Filter filter) {
        this.power = power;
        this.toughness = toughness;
        this.filter = filter;
    }

    @Override
    public void apply(MagicObject source, Game game) {

        if (source instanceof Permanent) {
            var amount = (int) game.getBattlefield().getAll().stream().filter(p -> filter.evaluate(p, game, source)).count();
            if (power > 0) {
                source.setPower(amount * power);
            }
            if (toughness > 0) {
                source.setToughness(amount * toughness);
            }
        }
    }

    public String getRule() {
        return "{this} gets +" + power + "/+" + toughness + " for each " + filter.getRule();
    }

}
