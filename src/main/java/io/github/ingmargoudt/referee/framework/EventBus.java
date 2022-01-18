package io.github.ingmargoudt.referee.framework;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private static final List<EventListener> listeners = new ArrayList<>();

    private EventBus() {
    }

    public static void registerListener(EventListener eventListener) {
        if (listeners.stream().noneMatch(listener -> listener.getClass().equals(eventListener.getClass()))) {
            listeners.add(eventListener);
        }

    }

    public static void report(String message) {
        listeners.forEach(listener -> listener.report(message));
    }


    public static void clear() {
        listeners.clear();
    }
}
