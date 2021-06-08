package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.base.TestPlayer;
import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.Zone;
import io.github.ingmargoudt.referee.players.Player;
import org.junit.jupiter.api.Test;

public class Test_simple_game extends BaseGame {

    private Card bears = new GrizzlyBears();

    @Test
    public void simplegame(){
        addCard(Zone.HAND, player1, bears, 1);

        castSpell(1, Phase.PRECOMBAT_MAINPHASE, player1,bears );
        stopAt(3, Phase.PRECOMBAT_MAINPHASE);
        start();
    }


}
