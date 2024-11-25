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

    List<Effect> effects = new ArrayList<>();
    Class<T> effectType;

    public Effects(Class<T> effectType) {
        this.effectType = effectType;
    }

    public Effects(T effect) {
        effects.add(effect);
        effectType = (Class<T>) effect.getClass();
    }

    public Effects(Effects<T> spellEffects) {
        this.effects = new ArrayList<>(spellEffects.effects);
        this.effectType = spellEffects.effectType;
    }


    public void addEffect(Effect effect) {
        if (effect instanceof ContinuousEffect continuousEffect && effectType == OneShotEffect.class) {
            ContinuousEffectInitializer ci = new ContinuousEffectInitializer(continuousEffect);
            if(effect instanceof TargetEffect targetEffect){
                ci.targets = targetEffect.getTargets();
            }
            effects.add(ci);
        } else {
            effects.add(effect);
        }
    }

    public String getRule() {
        return effects.stream().map(Ruleable::getRule).collect(Collectors.joining());
    }

    public void apply(MagicObject source, Game game) {
        effects.forEach(effect -> {
            if (effect instanceof ContinuousEffectInitializer continuousEffectInitializer) {
                continuousEffectInitializer.getContinuousEffect().lockInObjects(game, source);

            }
            effect.apply(source, game);
        });
    }

    public boolean hasTargets() {
        return effects.stream().anyMatch(effect -> (effect instanceof OneShotEffect oneShotEffect && oneShotEffect.hasTargets()) ||
                (effect instanceof ContinuousEffectInitializer continuousEffectInitializer &&
                        continuousEffectInitializer.continuousEffect instanceof TargetEffect &&
                        continuousEffectInitializer.hasTargets()));
    }

    public void chooseTargets(Stackable stackable, Game game) {
        effects.forEach(effect -> {
            if (effect instanceof TargetEffect targetEffect) {
                targetEffect.choose(stackable, game);
            }
            else if (effect instanceof ContinuousEffectInitializer continuousEffectInitializer ) {
                TargetEffect targetEffect = (TargetEffect) continuousEffectInitializer.continuousEffect;
                targetEffect.choose(stackable, game);
            }

        });
    }

    public boolean canBePlayed(Game game, Card card) {
        return effects.stream().filter(TargetEffect.class::isInstance).map(TargetEffect.class::cast).allMatch(target -> target.hasValidTargets(game, card));
    }
}
