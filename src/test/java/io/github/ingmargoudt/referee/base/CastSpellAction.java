package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CastSpellAction extends TestPlayerAction {

    @Getter
    private Card card;

    private List<Targetable> targets;


    public CastSpellAction(int turn, Phase phase, Card card){
        super(turn, phase);
        this.card = card;
    }


    public CastSpellAction(int turn, Phase phase, Card card, Targetable target){
        super(turn, phase);
        this.card = card;
        targets = new ArrayList<>();
        targets.add(target);
    }

    public Targetable consumeTarget(){
        return targets.remove(0);
    }

    @Override
    public void execute(TestPlayer player) {
        player.castSpell(card);
    }
}
