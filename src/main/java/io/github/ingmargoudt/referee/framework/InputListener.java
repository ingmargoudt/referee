package io.github.ingmargoudt.referee.framework;

import java.util.UUID;

public interface InputListener {

    String process(UUID source, String message);
}
