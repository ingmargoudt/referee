package io.github.ingmargoudt.referee.game.zones;

import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.Permanent;

import java.util.ArrayList;
import java.util.List;

public class Battlefield {

    private final List<Permanent> permanents = new ArrayList<>();

    public void add(Permanent permanent) {
        permanents.add(permanent);
    }

    public void resetBase() {
        permanents.forEach(Permanent::reset);
    }

    public List<Permanent> getAll() {
        return new ArrayList<>(permanents);
    }

    public void remove(Card card) {
        permanents.removeIf(permanent -> permanent.getId().equals(card.getId()));
    }
}
