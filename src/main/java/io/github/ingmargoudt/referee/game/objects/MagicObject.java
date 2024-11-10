package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.game.abilities.Abilities;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.effects.Effects;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.ReplacementEffect;
import io.github.ingmargoudt.referee.game.properties.Controllable;
import io.github.ingmargoudt.referee.game.properties.Ruleable;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;


@Getter
public class MagicObject extends BaseObject implements Ruleable, Controllable {

    /*
    109.3. An object’s characteristics are name, mana cost, color, color indicator, card type, subtype,
supertype, rules text, abilities, power, toughness, loyalty, hand modifier, and life modifier. Objects
can have some or all of these characteristics. Any other information about an object isn’t a
characteristic. For example, characteristics don’t include whether a permanent is tapped, a spell’s
target, an object’s owner or controller, what an Aura enchants, and so on.
     */

    protected String name;
    protected ManaCost manaCost;
    protected Set<Color> color;
    protected Color colorIndicator;
    protected CardTypes cardtypes;
    protected SubTypes subTypes;
    protected SuperTypes superTypes;
    protected String rulesText;
    protected Abilities abilities;
    @Setter
    protected int power;
    @Setter
    protected int toughness;
    protected int handmodifier;
    protected int lifemodifier;

    protected Effects<OneShotEffect> spellEffects;
    protected List<ReplacementEffect> replacementEffects;

    @Setter
    protected Player controller;


    public MagicObject(String name) {
        this.name = name;
        cardtypes = new CardTypes();
        subTypes = new SubTypes();
        superTypes = new SuperTypes();
        abilities = new Abilities();
        spellEffects = new Effects<>(OneShotEffect.class);
        replacementEffects = new ArrayList<>();
        color = new HashSet<>();
    }

    public Set<Color> getColor(){
        Set<Color> results = new HashSet<>();
        results.addAll(color);
        results.addAll(manaCost.getColors());
        return results;

    }

    public boolean isCreature() {
        return getCardtypes().isCreature();
    }

    public boolean hasAbility(Class<? extends Ability> abilityClass) {
        return getAbilities().has(abilityClass);
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    public void removeAbility(Class<? extends Ability> abilityClass) {
        abilities.remove(abilityClass);
    }

    public boolean isLand() {
        return getCardtypes().isLand();
    }

    public boolean isBasic() {
        return superTypes.isBasic();
    }


    public boolean hasColor(Color color) {
        return this.color.contains(color);
    }

    public boolean isSorcery() {
        return cardtypes.has(CardType.SORCERY);
    }

    @Override
    public String getRule() {
        var stringBuilder = new StringBuilder();
        var cardText = "";
        cardText += replacementEffects.stream().map(ReplacementEffect::getRule).collect(Collectors.joining("\n"));
        if (!cardText.isEmpty()) {
            cardText += "\n";
        }
        abilities.forEach(ability -> stringBuilder.append(ability.getRule()).append("\n"));
        cardText += stringBuilder.toString();
        cardText += spellEffects.getRule();
        cardText = cardText.replace("{this}", name);

        return cardText;
    }

    @Override
    public boolean isControlledBy(Player thePlayer) {
        return getController().equals(thePlayer);
    }
}
