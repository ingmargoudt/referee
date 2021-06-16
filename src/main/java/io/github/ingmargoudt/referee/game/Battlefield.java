package io.github.ingmargoudt.referee.game;

import java.util.*;
public class Battlefield {

    private List<Permanent> permanents = new ArrayList<>();

    public void add(Permanent permanent){
        permanents.add(permanent);
    }

    public void resetBase() {
        permanents.forEach(Permanent::reset);
    }

    public List<Permanent> getAll(){
        return new ArrayList<>(permanents);
    }

    public void remove(Card card) {
        permanents.removeIf(permanent -> permanent.getId().equals(card.getId()));
    }
}
