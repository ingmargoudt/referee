package io.github.ingmargoudt.referee.cards.c;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.targets.Filter;
import io.github.ingmargoudt.referee.game.targets.TargetPermanent;

import static io.github.ingmargoudt.referee.game.targets.OrPredicate.or;

public class Cryoclasm extends Card {
    public Cryoclasm() {
        super("Cryoclasm");
        cardtypes.add(CardType.SORCERY);
        getSpellEffects().addEffect(new CryoClasmEffect());
    }
}

class CryoClasmEffect extends OneShotEffect implements TargetEffect {

    public CryoClasmEffect() {
        targets.add(new TargetPermanent(Filter.by(or(SubType.ISLAND, SubType.PLAINS))));
    }

    @Override
    public void apply(MagicObject object, Game game) {
        targets.forEach(target ->
                target.resolve(game).ifPresent(targetable -> {
                        ((Permanent) targetable).destroy(game);
                        targetable.getController().receiveDamage(game, object, 3);
                }));
    }

    @Override
    public String getRule() {
        return "Destroy " + targets.get(0).getRule() + ". {this} deals 3 damage to that land's controller";
    }
}
