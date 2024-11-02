package io.github.ingmargoudt.referee.cards.l;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.statics.Flying;
import io.github.ingmargoudt.referee.game.abilities.TriggeredAbility;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class LifecreedDuo extends Card {
    public LifecreedDuo() {
        super("Lifecreed Duo");
        cardtypes.add(CardType.CREATURE);
        subTypes.add(SubType.BAT, SubType.BIRD);
        setPower(1);
        setToughness(2);
        addAbility(Flying.getInstance());
        addAbility(new AnotherCreatureYouControlEntersAbility());
    }


}

class AnotherCreatureYouControlEntersAbility extends TriggeredAbility {
    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {
        if(event instanceof EnterTheBattlefieldEvent){
            if(event.isControlledBy(parentObject.getController())){
                if(event.getSource().isCreature() && event.getSource().getId() != parentObject.getId()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        source.getController().gainLife(game, 1, source);
    }

    @Override
    public String getRule() {
        return "When another creature you control enters, you gain 1 life.";
    }
}