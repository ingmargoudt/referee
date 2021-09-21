package io.github.ingmargoudt.referee.game;

import java.util.*;

public class SuperTypes {
    List<SuperType> superTypes = new ArrayList<>();
    public boolean isBasic() {
        return superTypes.contains(SuperType.Basic);
    }
}
