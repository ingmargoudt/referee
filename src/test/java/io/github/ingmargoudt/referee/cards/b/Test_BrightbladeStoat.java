package io.github.ingmargoudt.referee.cards.b;

import io.github.ingmargoudt.referee.base.BaseGame;
import io.github.ingmargoudt.referee.game.abilities.statics.FirstStrike;
import io.github.ingmargoudt.referee.game.abilities.statics.Lifelink;
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
