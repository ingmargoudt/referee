package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.game.abilities.Abilities;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
public class MagicObject extends BaseObject {

    /*
    109.3. An object’s characteristics are name, mana cost, color, color indicator, card type, subtype,
supertype, rules text, abilities, power, toughness, loyalty, hand modifier, and life modifier. Objects
can have some or all of these characteristics. Any other information about an object isn’t a
characteristic. For example, characteristics don’t include whether a permanent is tapped, a spell’s
target, an object’s owner or controller, what an Aura enchants, and so on.
     */

    private String name;
    private ManaCost manaCost;
    private Color color;
    private Color colorIndicator;
    private CardTypes cardtypes;
    private SubTypes subTypes;
    private SuperTypes superTypes;
    private String rulesText;
    private Abilities abilities;
    @Setter
    private int power;
    @Setter
    private int toughness;
    private int handmodifier;
    private int lifemodifier;

    private List<OneShotEffect> spellEffects;

    @Setter
    private UUID controller;


    public MagicObject(String name) {
        this.name = name;
        cardtypes = new CardTypes();
        subTypes = new SubTypes();
        superTypes = new SuperTypes();
        abilities = new Abilities();
        spellEffects = new ArrayList<>();
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


    protected void removeAbility(Class<? extends Ability> abilityClass) {
        getAbilities().remove(abilityClass);

    }
}
