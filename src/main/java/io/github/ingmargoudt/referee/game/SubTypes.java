package io.github.ingmargoudt.referee.game;

import java.util.*;

public class SubTypes {

    List<SubType> subTypeList = new ArrayList<>();

    public void add(SubType subType){
        subTypeList.add(subType);
    }

    public boolean has(SubType subType){
        return subTypeList.contains(subType);
    }

    public void addAll(SubTypes subTypes) {
        subTypeList.addAll(subTypes.subTypeList);
    }

    public void clear() {
        subTypeList.clear();
    }
}
