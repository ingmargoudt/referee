package io.github.ingmargoudt.referee.framework;

import java.util.ArrayList;
import java.util.*;

public class TestInputListener extends InputListener {

    Map<UUID, List<String>> commands = new HashMap<>();

    public void addInput(UUID uuid, String input){
        commands.putIfAbsent(uuid, new ArrayList<>());
        commands.get(uuid).add(input);
    }

    @Override
    public String process(UUID source, String message) {
        return commands.get(source).remove(0);
    }
}
