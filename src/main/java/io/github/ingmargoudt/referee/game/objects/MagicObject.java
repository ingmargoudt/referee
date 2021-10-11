package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.game.abilities.Abilities;
import io.github.ingmargoudt.referee.game.abilities.Ability;
import io.github.ingmargoudt.referee.game.effects.OneShotEffect;
import io.github.ingmargoudt.referee.game.effects.ReplacementEffect;
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

    protected String name;
    protected ManaCost manaCost;
    protected Color color;
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

    protected List<OneShotEffect> spellEffects;
    protected List<ReplacementEffect> replacementEffects;

    @Setter
    protected UUID controller;


    public MagicObject(String name) {
        this.name = name;
        cardtypes = new CardTypes();
        subTypes = new SubTypes();
        superTypes = new SuperTypes();
        abilities = new Abilities();
        spellEffects = new ArrayList<>();
        replacementEffects = new ArrayList<>();
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

    public boolean isLand() {
        return getCardtypes().isLand();
    }

    public boolean isBasic() {
        return superTypes.isBasic();
    }
}
