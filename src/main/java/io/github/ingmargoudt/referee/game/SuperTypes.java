package io.github.ingmargoudt.referee.game;

import java.util.ArrayList;
import java.util.List;

public class SuperTypes {
    List<SuperType> supertypesList = new ArrayList<>();
    public boolean isBasic() {
        return supertypesList.contains(SuperType.BASIC);
    }
}
