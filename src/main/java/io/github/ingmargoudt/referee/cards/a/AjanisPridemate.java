package io.github.ingmargoudt.referee.cards.a;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.CounterType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.TriggeredAbility;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.events.GainLifeEvent;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class AjanisPridemate extends Card {

    public AjanisPridemate(){
        super("Ajani's Pridemate");
        this.setPower(2);
        this.setToughness(2);
        this.cardtypes.add(CardType.CREATURE);
        this.subTypes.add(SubType.CAT);
        addAbility(new AjanisPridemateAbility());
    }


}

class AjanisPridemateAbility extends TriggeredAbility {
    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {
        if(event instanceof GainLifeEvent){
            if(event.isControlledBy(parentObject.getController())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        source.addCounter(CounterType.P1P1);
    }

    @Override
    public String getRule() {
        return "";
    }
}
