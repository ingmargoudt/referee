package io.github.ingmargoudt.referee.game;

import java.util.*;
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
    private List<Ability> abilities;
    private int power;
    private int toughness;
    private int handmodifier;
    private int lifemodifier;
}
