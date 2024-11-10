package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.targets.Filter;
import io.github.ingmargoudt.referee.players.Player;

public class BoostAllCreatures extends ContinuousEffect {

    private final int toughness;
    private final int power;

    public BoostAllCreatures(Filter filter, int power, int toughness) {
        this.power = power;
        this.toughness = toughness;
        this.filter = filter;
    }


    public void apply(MagicObject source, Game game) {
            for (var permanent : game.getBattlefield().getAll()) {
                if (permanent.isCreature() && filter.evaluate(permanent, game, source)) {
                    EventBus.report("Applying " + source.getController().getName() + "'s " + source.getName() + " " + getClass().getSimpleName() + " to " + source.getController().getName() + "'s " + permanent.getName());
                    permanent.setPower(permanent.getPower() + power);
                    permanent.setToughness(permanent.getToughness() + toughness);
                }
            }
    }

    @Override
    public String getRule() {
            return "All " + filter.getRule() + "creatures get +" + power + "/+" + toughness;
    }
}
