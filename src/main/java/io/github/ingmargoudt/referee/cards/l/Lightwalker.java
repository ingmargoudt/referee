package io.github.ingmargoudt.referee.cards.l;

import io.github.ingmargoudt.referee.game.CounterType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.abilities.statics.Flying;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class Lightwalker extends Card {
    public Lightwalker(String name) {
        super("Lightwalker");
        setPower(2);
        setPower(1);
        addAbility(new LightWalkerFlyingAbility());
    }
}
class LightWalkerFlyingAbility extends Ability {
    @Override
    public void resolve(MagicObject source, Game game) {
        if(source.hasCounter(CounterType.P1P1)){
            source.addAbility(Flying.getInstance());
        }
    }

    @Override
    public String getRule() {
        return "Lightwalker has flying as long as it has a +1/+1 counter on it.";
    }
}