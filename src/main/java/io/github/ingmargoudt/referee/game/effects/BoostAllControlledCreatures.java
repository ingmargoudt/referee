package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.Permanent;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.players.Player;

import java.util.UUID;

public class BoostAllControlledCreatures extends ContinuousEffect {

    private final int toughness;
    private final int power;

    public BoostAllControlledCreatures(int power, int toughness){
        this.power = power;
        this.toughness = toughness;
        //this.source = source
    }


    public void apply(Ability ability, Game game){
        Card card = game.getCard(ability.getSource());
        game.getPlayer(card.getController()).ifPresent(controller -> {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.isControlledBy(controller) && permanent.isCreature()) {
                    EventBus.report("Applying " + card.getName() + " " + getClass().getSimpleName() + " to " + controller.getName() + "'s " + permanent.getName());
                    permanent.setPower(permanent.getPower() + power);
                    permanent.setToughness(permanent.getToughness() + toughness);
                }
            }
        });

    }
}
