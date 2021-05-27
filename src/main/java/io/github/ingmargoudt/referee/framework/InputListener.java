package io.github.ingmargoudt.referee.framework;

import java.util.UUID;

public abstract class InputListener {

    public abstract String process(UUID source, String message);
}
