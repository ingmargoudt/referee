package io.github.ingmargoudt.referee.cards.s;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.DrawsACardAbility;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
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
           if(reflectedSource instanceof Player){
               if(reflectedSource != object.getController()) {
                   ((Player) reflectedSource).loseLife(2);
               }
               if(reflectedSource == object.getController()){
                   ((Player) reflectedSource).gainLife(game, 2, object);
               }
           }
        }

        @Override
        public String getRule() {
            return "";
        }
    }

}
