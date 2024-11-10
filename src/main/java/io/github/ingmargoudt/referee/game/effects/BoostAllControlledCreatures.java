package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.DurationType;
import io.github.ingmargoudt.referee.game.targets.ControlledByPlayerSelector;
import io.github.ingmargoudt.referee.game.targets.Filter;

import java.util.List;
import java.util.stream.Collectors;

public class BoostAllControlledCreatures extends ContinuousEffect {

    private final int toughness;
    private final int power;

    public BoostAllControlledCreatures(int power, int toughness) {
        this.power = power;
        this.toughness = toughness;
        this.filter = Filter.by(CardType.CREATURE, ControlledByPlayerSelector.YOU);
    }

    public BoostAllControlledCreatures(int power, int toughness, DurationType durationType) {
        super(durationType);
        this.power = power;
        this.toughness = toughness;
        this.filter = Filter.by(CardType.CREATURE, ControlledByPlayerSelector.YOU);
    }

    @Override
    public void lockInObjects(Game game, MagicObject source) {
        getAffectedObjects().addAll(search(game, source));
        setAffectedObjectsDetermined(true);
    }

    private List<Permanent> search(Game game, MagicObject source) {
        return game.getBattlefield().getAll().stream().filter(f -> filter.evaluate(f, game, source)).collect(Collectors.toList());
    }


    public void apply(MagicObject source, Game game) {

        if (getDuration().getDurationType() == DurationType.CONTINUOUS) {
            for (var permanent : search(game, source)) {
                EventBus.report("Applying " + source.getName() + " " + getClass().getSimpleName() + " to " + source.getController().getName() + "'s " + permanent.getName());
                permanent.setPower(permanent.getPower() + power);
                permanent.setToughness(permanent.getToughness() + toughness);
            }
        } else {
            for (var magicObject : getAffectedObjects()) {
                Permanent permanent = (Permanent) magicObject;
                permanent.setPower(permanent.getPower() + power);
                permanent.setToughness(permanent.getToughness() + toughness);
            }
        }
    }


    @Override
    public String getRule() {
        return "All " + filter.getRule() + "get +" + power + "/+" + toughness + getDuration().toString();
    }
}
