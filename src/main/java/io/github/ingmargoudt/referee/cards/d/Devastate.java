package io.github.ingmargoudt.referee.cards.d;

import io.github.ingmargoudt.referee.game.CardType;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.targets.Filter;
import io.github.ingmargoudt.referee.game.targets.TargetPermanent;

import java.util.stream.Collectors;

public class Devastate extends Card {
    public Devastate() {
        super("Devastate");
        cardtypes.add(CardType.SORCERY);
        getSpellEffects().addEffect(new DevastateEffect());
    }
}

class DevastateEffect extends OneShotEffect implements TargetEffect {

    public DevastateEffect() {
        targets.add(new TargetPermanent(Filter.by(CardType.LAND)));
    }

    @Override
    public void apply(MagicObject object, Game game) {
        game.getPlayer(object.getController()).ifPresent(controllerOfSpell ->

                targets.forEach(target ->
                        target.resolve(game).ifPresent(targetable -> {
                            if (targetable instanceof Permanent) {
                                ((Permanent) targetable).destroy(game);
                                game.getBattlefield().getAll().stream().filter(MagicObject::isCreature).collect(Collectors.toList()).forEach(creature -> creature.damage(controllerOfSpell, object, 1));
                                game.getPlayers().forEach(player -> player.damage(controllerOfSpell, object, 1));
                            }
                        })));
    }

    @Override
    public String getRule() {
        return "Destroy " + targets.get(0).getRule() + ". {this} deals 1 damage to each creature and each player";
    }
}
