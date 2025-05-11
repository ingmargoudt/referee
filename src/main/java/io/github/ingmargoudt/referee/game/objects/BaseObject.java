package io.github.ingmargoudt.referee.game.objects;

import io.github.ingmargoudt.referee.game.CounterType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class BaseObject {

    protected final UUID id;

    protected Map<CounterType, Integer> counters = new HashMap<>();

    public BaseObject() {
        id = UUID.randomUUID();
    }

    public void addCounter(CounterType counterType, int amount) {
        counters.put(counterType, counters.getOrDefault(counterType, 0) + amount);
    }
    public void removeCounter(CounterType counterType, int amount) {
        counters.put(counterType, counters.getOrDefault(counterType, 0) - amount);
        if (counters.get(counterType) <= 0) {
            counters.remove(counterType);
        }
    }

    public void addCounter(CounterType counterType) {
        addCounter(counterType, 1);
    }

    public void removeCounter(CounterType counterType) {
        removeCounter(counterType, 1);
    }

    public void removeCounters(){
        counters.clear();
    }

    public boolean hasCounter(CounterType counterType) {
        return counters.containsKey(counterType);
    }
    public boolean hasCounters(){
        return !counters.isEmpty();
    }
}
