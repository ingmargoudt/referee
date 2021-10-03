package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.MagicObject;
import io.github.ingmargoudt.referee.game.TargetCreatureOrPlayer;

public class DamageToAnyTargetEffect extends OneShotEffect implements TargetEffect {

    private int amount;

    public DamageToAnyTargetEffect(int amount) {
        this.amount = amount;
        targets.add(new TargetCreatureOrPlayer());
    }

    @Override
    public void apply(MagicObject object, Game game) {
        targets.get(0).resolve(game).ifPresent(theTarget -> theTarget.damage(amount));

    }

    @Override
    public boolean hasValidTargets(Game game) {
        if (game.getBattlefield().getAll().stream().noneMatch(MagicObject::isCreature)) {
            return false;
        }
        return true;
    }

}
