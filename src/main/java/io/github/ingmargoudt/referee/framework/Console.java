package io.github.ingmargoudt.referee.framework;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Console implements EventListener{
    @Override
    public void report(String message) {
        log.info(message);
    }
}
