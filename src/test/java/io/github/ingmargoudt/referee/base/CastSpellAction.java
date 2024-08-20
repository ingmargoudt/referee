package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CastSpellAction extends TestPlayerAction {

    @Getter
    private Card card;

    private List<Targetable> targets = new ArrayList<>();


    public CastSpellAction(int turn, Phase phase, Card card) {
        super(turn, phase);
        this.card = card;
    }


    public CastSpellAction(int turn, Phase phase, Card card, Targetable target) {
        super(turn, phase);
        this.card = card;
        targets.add(target);
    }

    public CastSpellAction(int turn, Step step, Card card, Targetable target) {
        super(turn, step);
        this.card = card;
        targets.add(target);
    }

    public Targetable consumeTarget() {
        return targets.remove(0);
    }

    public String toString() {
        String cast = "Cast " + card.getName();
        if (!targets.isEmpty()) {
            cast += " targeting " + targets.get(0).getName();
        }
        return cast;
    }

    @Override
    public void execute(TestPlayer player) {
        player.castSpell(card);
    }
}
