package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;

public class AttackAction extends TestPlayerAction{

    private Card card;

    public AttackAction(int turn, Card card) {
        super(turn, Phase.COMBAT_PHASE);
        this.card = card;
    }

    @Override
    public void execute(TestPlayer player) {
        player.declareAttacker(card);
    }
}
