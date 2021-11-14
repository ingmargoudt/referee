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
        game.getPlayer(source.getController()).ifPresent(controller -> {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.isCreature() && filter.evaluate(permanent, game, source)) {
                    EventBus.report("Applying " + controller.getName() + "'s " + source.getName() + " " + getClass().getSimpleName() + " to " + game.getPlayer(permanent.getController()).map(Player::getName) + "'s " + permanent.getName());
                    permanent.setPower(permanent.getPower() + power);
                    permanent.setToughness(permanent.getToughness() + toughness);
                }
            }
        });
    }

    @Override
    public String getRule() {
        return "All " + filter.getRule() + " get +"+power +"/+"+toughness;
    }
}
