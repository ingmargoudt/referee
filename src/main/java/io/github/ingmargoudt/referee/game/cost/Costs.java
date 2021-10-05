package io.github.ingmargoudt.referee.game.cost;

import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.objects.MagicObject;

import java.util.ArrayList;
import java.util.List;

public class Costs {

    private List<Cost> theCosts = new ArrayList<>();

    public static Costs of(Cost ...costs){
        Costs c = new Costs();
        for (Cost cost : costs) {
            c.addCost(cost);
        }
        return c;
    }

    public void addCost(Cost cost){
        theCosts.add(cost);
    }

    public boolean canPay(MagicObject source, Game game){
        return theCosts.stream().allMatch(cost -> cost.canPay(source, game));
    }

    public void payAll(MagicObject source, Game game){
        theCosts.forEach(cost -> cost.pay(source, game));

    }
}
