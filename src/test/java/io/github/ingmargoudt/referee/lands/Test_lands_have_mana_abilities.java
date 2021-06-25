package io.github.ingmargoudt.referee.lands;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.cards.m.Mountain;
import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.zones.Zone;
import io.github.ingmargoudt.referee.game.abilities.AddRedManaAbility;
import org.junit.jupiter.api.Test;

public class Test_lands_have_mana_abilities extends BaseGame {

    private static final Card mountain= new Mountain();

    @Test
    public void mountain(){
        addCard(Zone.BATTLEFIELD, player1, mountain, 1);
        stopAt(1, Phase.PRECOMBAT_MAINPHASE);
        start();
        assertPermanentHasAbility(player1, mountain, AddRedManaAbility.class);

    }
}
