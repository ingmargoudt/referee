package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.abilities.FirstStrike;
import io.github.ingmargoudt.referee.game.abilities.Lifelink;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.zones.Zone;
import org.junit.jupiter.api.Test;

public class Test_BrightbladeStoat extends BaseGame {

    @Test
    void checkAbilities(){

        Card brightbladeStoat = new BrightbladeStoat();
        addCard(Zone.BATTLEFIELD, player1, brightbladeStoat);
        assertPermanentHasAbility(player1, brightbladeStoat, FirstStrike.class);
        assertPermanentHasAbility(player1, brightbladeStoat, Lifelink.class);
    }
}
