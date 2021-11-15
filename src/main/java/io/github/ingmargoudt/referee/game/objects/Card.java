package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.effects.TargetEffect;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Card extends MagicObject implements Targetable, Ruleable {

    @Setter
    UUID owner;

    public Card(String name) {
        super(name);
    }


    public boolean isPermanent() {
        return getCardtypes().isPermanent();
    }

    public boolean canBePlayed(Game game) {
        return getSpellEffects().canBePlayed(game, this);
    }

    @Override
    public String getRule() {
        StringBuilder stringBuilder = new StringBuilder();

        abilities.forEach(ability -> stringBuilder.append(ability.getRule()));
        String cardtext = stringBuilder.toString();
        cardtext = cardtext.replace("{this}", name);
        return cardtext;
    }
}
