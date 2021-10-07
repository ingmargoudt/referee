package io.github.ingmargoudt.referee.framework;

import java.util.UUID;

public class Question {

    private Question() {
    }

    public static boolean askYesNo(UUID source, String message) {
        String input = InputBus.process(source, message);
        while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
            input = InputBus.process(source, message);
        }
        return input.equalsIgnoreCase("yes");

    }
}
