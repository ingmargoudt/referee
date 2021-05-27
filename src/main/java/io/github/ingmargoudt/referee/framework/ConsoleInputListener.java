package io.github.ingmargoudt.referee.framework;


import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@Log4j2
public class ConsoleInputListener extends InputListener {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String process(UUID uuid, String message) {
        EventBus.report(message);
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
