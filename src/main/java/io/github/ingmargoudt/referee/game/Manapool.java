package io.github.ingmargoudt.referee.game;


import lombok.Getter;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


public class Manapool {

    @Getter
    Map<ManaType, Integer> pool = new EnumMap<>(ManaType.class);

    public void add(ManaType... manaTypes) {
        for (ManaType manaType : manaTypes) {
            int amount = pool.getOrDefault(manaType, 0) + 1;
            pool.put(manaType, amount);
        }
    }
}
