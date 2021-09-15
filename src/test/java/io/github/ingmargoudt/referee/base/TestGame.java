package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Card;
import io.github.ingmargoudt.referee.game.zones.Battlefield;
import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.players.Player;

public class TestGame extends Game {

    public Battlefield getBattlefield(){
        return battlefield;
    }

    public Player[] getPlayers(){
        return players;
    }

}
