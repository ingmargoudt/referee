package io.github.ingmargoudt.referee.game.targets;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.objects.Spell;
import io.github.ingmargoudt.referee.game.properties.Targetable;

public class CardTypePredicate implements Predicate {

    CardType theCardType;

    public CardTypePredicate(CardType cardType) {
        this.theCardType = cardType;
    }

    @Override
    public boolean evaluate(Targetable target, Game game, MagicObject source) {
        if (target instanceof Spell) {
            return ((Spell) target).getCard().getCardtypes().has(theCardType);
        }
        if(target instanceof Permanent){
            return ((Permanent) target).getCardtypes().has(theCardType);
        }
        return false;
    }

    @Override
    public String toString(){
        return theCardType.toString();
    }
}
