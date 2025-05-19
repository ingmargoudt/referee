package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.TriggeredAbility;
import io.github.ingmargoudt.referee.game.events.EnterTheBattlefieldEvent;
import io.github.ingmargoudt.referee.game.events.Event;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class SoulWarden extends Card {
    public SoulWarden() {
        super("Soul Warden", "{W}");
        cardtypes.add(CardType.CREATURE);
        setPower(1);
        setToughness(1);
        this.addAbility(new SoulWardenGainLifeAbility());
    }


}
class SoulWardenGainLifeAbility extends TriggeredAbility {
    @Override
    public boolean checkTrigger(Event event, MagicObject parentObject) {
        if(event instanceof EnterTheBattlefieldEvent){
            return event.getSource().isCreature();
        }
        return false;
    }

    @Override
    public void resolve(MagicObject source, Game game) {
        source.getController().gainLife(game, 1, source);
    }

    @Override
    public String getRule() {
        return "Whenever another creature enters, you gain 1 life.";
    }
}