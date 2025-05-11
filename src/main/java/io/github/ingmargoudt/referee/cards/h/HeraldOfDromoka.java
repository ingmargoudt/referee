package io.github.ingmargoudt.referee.cards.h;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.abilities.statics.Vigilance;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

public class HeraldOfDromoka extends Card {
    public HeraldOfDromoka() {
        super("Herald of Dromoka");
        addAbility(new HeraldOfDromokaAbility());
        cardtypes.add(CardType.CREATURE);
    }
}
class HeraldOfDromokaAbility extends Ability{

    @Override
    public void resolve(MagicObject source, Game game) {
        game.getBattlefield().getAll().stream()
                .filter(p->p.isControlledBy(source.getController()))
                .filter(MagicObject::isCreature)
                .filter(p->!p.getId().equals(source.getId()))
                .filter(c->c.getSubTypes().has(SubType.WARRIOR))
                .forEach(c -> c.addAbility(Vigilance.getInstance()));

    }

    @Override
    public String getRule() {
        return "Other warrior creatures you control have vigilance.";
    }
}