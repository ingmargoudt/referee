package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.triggered.DrawsACardAbility;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.events.DrawCardEvent;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.players.Player;

public class SheoldredTheApocalypse extends Card {
    public SheoldredTheApocalypse() {
        super("Sheoldred, the Apocalypse");
        cardtypes.add(CardType.CREATURE);
        setPower(4);
        setToughness(5);
        addAbility(new DrawsACardAbility(new SheoldredDrawAndLoseOrGainLifeEffect()));
    }
    private class SheoldredDrawAndLoseOrGainLifeEffect extends OneShotEffect {
        @Override
        public void apply(MagicObject object, Game game) {
           if(reflectedSource instanceof DrawCardEvent){
               DrawCardEvent drawCardEvent = (DrawCardEvent) reflectedSource;
               if(drawCardEvent.getPlayer() != object.getController()) {
                   drawCardEvent.getPlayer().loseLife(2);
               }
               if(drawCardEvent.getPlayer() == object.getController()){
                   drawCardEvent.getPlayer().gainLife(game, 2, object);
               }
           }
        }

        @Override
        public String getRule() {
            return "";
        }
    }

}
