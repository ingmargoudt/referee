package io.github.ingmargoudt.referee.framework;

import java.util.*;

public class EventBus {

    private EventBus(){}
    private static List<EventListener> listeners = new ArrayList<>();

    public static void registerListener(EventListener eventListener){
        Class c = eventListener.getClass();
        if(listeners.stream().noneMatch(listener -> listener.getClass().equals(eventListener.getClass()))){
            listeners.add(eventListener);
        }

    }

    public static void report(String message){
        listeners.forEach(listener -> listener.report(message));
    }


}
