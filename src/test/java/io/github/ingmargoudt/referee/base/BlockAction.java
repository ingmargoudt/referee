package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.objects.Card;

public class BlockAction extends TestPlayerAction{

    private Card card;
    private Card toBlock;

    public BlockAction(int turn, Card card, Card toBlock) {
        super(turn, Phase.COMBAT_PHASE);
        this.card = card;
        this.toBlock = toBlock;
    }

    @Override
    public void execute(TestPlayer player) {
        player.declareBlocker(card, toBlock);
    }
}
