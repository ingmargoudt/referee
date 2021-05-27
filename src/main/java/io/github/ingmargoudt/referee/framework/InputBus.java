package io.github.ingmargoudt.referee.framework;

import java.util.UUID;

public class InputBus {

    private InputBus(){}
    private static InputListener listener = null;

    public static void registerListener(InputListener inputListener){
        listener = inputListener;
    }

    public static String process(UUID source, String message){
        return listener.process(source, message);
    }

}
