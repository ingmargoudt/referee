package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.DurationType;
import io.github.ingmargoudt.referee.game.targets.ControlledByPlayerSelector;
import io.github.ingmargoudt.referee.game.targets.Filter;

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
    public void lockInObjects(Game game, MagicObject source){
        getAffectedObjects().addAll(game.getBattlefield().getAll().stream().filter(f -> filter.evaluate(f, game, source)).collect(Collectors.toList()));
        setAffectedObjectsDetermined(true);
    }


    public void apply(MagicObject source, Game game) {

        game.getPlayer(source.getController()).ifPresent(controller -> {
            if(getDuration().getDurationType() == DurationType.CONTINUOUS) {
                for (Permanent permanent : game.getBattlefield().getAll().stream().filter(f -> filter.evaluate(f, game, source)).collect(Collectors.toList())) {
                    EventBus.report("Applying " + source.getName() + " " + getClass().getSimpleName() + " to " + controller.getName() + "'s " + permanent.getName());
                    permanent.setPower(permanent.getPower() + power);
                    permanent.setToughness(permanent.getToughness() + toughness);
                }
            }
            else{
                for(MagicObject magicObject : getAffectedObjects()){
                    Permanent permanent = (Permanent) magicObject;
                    permanent.setPower(permanent.getPower() + power);
                    permanent.setToughness(permanent.getToughness() + toughness);
                }
            }
        });
    }

    @Override
    public String getRule() {
        return "All " + filter.getRule() + "get +" + power + "/+" + toughness;
    }
}
