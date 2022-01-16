package io.github.ingmargoudt.referee.game.abilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Abilities implements Iterable<Ability> {

    private List<Ability> abilityList = new ArrayList<>();

    @Override
    public Iterator<Ability> iterator() {
        return abilityList.iterator();
    }


    public boolean has(Class<? extends Ability> abilityClass) {
        return abilityList.stream().map(Ability::getClass).anyMatch(cl -> cl.equals(abilityClass));
    }

    public void add(Ability ability) {
        abilityList.add(ability);
    }

    public void addAll(Abilities abilities) {
        abilities.forEach(ability -> abilityList.add(ability));
    }

    public void remove(Class<? extends Ability> abilityClass) {
        abilityList.removeIf(ability -> ability.getClass().equals(abilityClass));
    }

    public void clear() {
        abilityList.clear();
    }

    public int count() {
        return abilityList.size();
    }
}
