package io.github.ingmargoudt.referee.game.effects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.game.properties.Stackable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Effects<T extends Effect> {

    List<T> effects = new ArrayList<>();

    public Effects() {

    }

    public Effects(Effects<T> spellEffects) {
        this.effects = spellEffects.effects;
    }


    public void addEffect(T effect) {
        effects.add(effect);
    }

    @Override
    public String toString() {
        return effects.stream().map(Ruleable::getRule).collect(Collectors.joining());
    }

    public void apply(MagicObject source, Game game) {
        effects.forEach(effect -> effect.apply(source, game));
    }

    public boolean hasTargets() {
        return effects.stream().anyMatch(effect -> effect instanceof OneShotEffect && ((OneShotEffect) effect).hasTargets());
    }

    public void chooseTargets(Stackable stackable, Game game) {
        effects.forEach(effect -> {
            if (effect instanceof TargetEffect) {
                TargetEffect targetEffect = (TargetEffect) effect;
                targetEffect.choose(stackable, game);
            }
        });
    }

    public boolean canBePlayed(Game game, Card card) {
        return effects.stream().filter(TargetEffect.class::isInstance).map(TargetEffect.class::cast).allMatch(target -> target.hasValidTargets(game, card));
    }
}
