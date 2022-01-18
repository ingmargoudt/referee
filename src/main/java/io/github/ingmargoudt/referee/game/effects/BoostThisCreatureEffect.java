package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.DurationType;

public class BoostThisCreatureEffect extends ContinuousEffect {

    final int power;
    final int toughness;

    public BoostThisCreatureEffect(int power, int toughness, DurationType durationType) {
        super(durationType);
        this.power = power;
        this.toughness = toughness;

    }


    @Override
    public void apply(MagicObject source, Game game) {

        if (source instanceof Permanent) {
            if (power > 0) {
                source.setPower(source.getPower() +  power);
            }
            if (toughness > 0) {
                source.setToughness(source.getToughness() + toughness);
            }
        }
    }

    public String getRule() {
        return "{this} gets +" + power + "/+" + toughness + getDuration().toString();
    }

}
