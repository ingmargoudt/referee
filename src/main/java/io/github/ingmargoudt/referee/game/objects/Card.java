package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.ManaCost;
import io.github.ingmargoudt.referee.game.effects.ReplacementEffect;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class Card extends MagicObject implements Targetable, Ruleable {

    @Setter
    Player owner;

    public Card(String name) {
        super(name);
    }

    public Card(String name, String manaCost) {
        super(name);
        this.manaCost = new ManaCost(manaCost);
    }


    public boolean isPermanent() {
        return getCardtypes().isPermanent();
    }

    public boolean canBePlayed(Game game) {
        return getSpellEffects().canBePlayed(game, this);
    }

    @Override
    public String getRule() {
        var stringBuilder = new StringBuilder();
        var cardText = "";
        cardText += replacementEffects.stream().map(ReplacementEffect::getRule).collect(Collectors.joining("\n"));
        if(!cardText.isEmpty()){
            cardText += "\n";
        }
        abilities.forEach(ability -> stringBuilder.append(ability.getRule()).append("\n"));
        cardText += stringBuilder.toString();
        cardText += spellEffects.getRule();
        cardText = cardText.replace("{this}", name);

        return cardText;
    }
}
