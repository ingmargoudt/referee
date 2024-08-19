package io.github.ingmargoudt.referee.game;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
public class SubTypes {

    List<SubType> subTypeList = new ArrayList<>();

    public void add(SubType subType) {
        subTypeList.add(subType);
    }

    public void add(SubType... subTypes){
        subTypeList.addAll(Arrays.asList(subTypes));
    }

    public boolean has(SubType subType) {
        return subTypeList.contains(subType);
    }

    public void addAll(SubTypes subTypes) {
        subTypeList.addAll(subTypes.subTypeList);
    }

    public void clear() {
        subTypeList.clear();
    }

    @Override
    public String toString() {
        return subTypeList.toString();
    }
}
