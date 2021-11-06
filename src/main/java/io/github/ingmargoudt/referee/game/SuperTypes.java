package io.github.ingmargoudt.referee.game;

import java.util.ArrayList;
import java.util.List;

public class SuperTypes {
    List<SuperType> supertypesList = new ArrayList<>();

    public boolean isBasic() {
        return supertypesList.contains(SuperType.BASIC);
    }

    public void add(SuperType superType){
        supertypesList.add(superType);
    }

    public boolean has(SuperType theSuperType) {
        return supertypesList.contains(theSuperType);
    }

    public void clear() {
        supertypesList.clear();
    }

    public void addAll(SuperTypes superTypes) {
        supertypesList.addAll(superTypes.supertypesList);
    }
}
