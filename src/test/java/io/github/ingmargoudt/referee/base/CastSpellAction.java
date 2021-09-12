package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.players.Player;

public class CastSpellAction extends TestPlayerAction {

    private Card card;


    public CastSpellAction(int turn, Phase phase, Card card){
        super(turn, phase);
        this.card = card;
    }

    @Override
    public void execute(TestPlayer player) {
        player.castSpell(card);
    }
}
