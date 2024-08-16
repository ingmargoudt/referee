package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.DurationType;
import io.github.ingmargoudt.referee.game.properties.Stackable;
import io.github.ingmargoudt.referee.game.targets.Target;
import io.github.ingmargoudt.referee.game.targets.TargetCreature;

import java.util.Arrays;
import java.util.List;

public class BoostTargetCreatureEffect extends ContinuousEffect implements TargetEffect {

    final int power;
    final int toughness;
    final TargetCreature targetCreature;

    public BoostTargetCreatureEffect(int power, int toughness, DurationType durationType) {
        super(durationType);
        this.power = power;
        this.toughness = toughness;
        targetCreature = new TargetCreature();
    }

    @Override
    public void apply(MagicObject source, Game game) {
        targetCreature.resolve(game)
                .map(Permanent.class::cast).ifPresent(permanent -> {
                permanent.setPower(permanent.getPower() + power);
                permanent.setToughness(permanent.getToughness() + toughness);
        });
    }

    public String getRule() {
        return "Target creature gets +" + power + "/+" + toughness + getDuration().toString();
    }

    @Override
    public boolean hasValidTargets(Game game, MagicObject source) {
        return !targetCreature.from(source).validTargets(game).isEmpty();
    }

    @Override
    public void choose(Stackable stackable, Game game) {
            targetCreature.choose(stackable, game, this);
            //getAffectedObjects().add((Permanent) targetCreature.resolve(game));
    }


    @Override
    public List<Target> getTargets() {
        return Arrays.asList(targetCreature);
    }
}
