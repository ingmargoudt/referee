package io.github.ingmargoudt.referee.comprehensiverules._1_Game_Concepts._100_General;

import io.github.ingmargoudt.referee.base.BaseGame;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Test_100 extends BaseGame {

    //100.1. These Magic rules apply to any Magic game with two or more players, including two-player
    //games and multiplayer games.

    //100.1a A two-player game is a game that begins with only two players.
    @Test
    void _1a(){
        assertThat(getPlayers()).hasSize(2);

    }

    //100.1b A multiplayer game is a game that begins with more than two players. See section 8,
    //“Multiplayer Rules.”
    @Disabled
    @Test
    void _1b(){

    }

    //100.2. To play, each player needs their own deck of traditional Magic cards, small items to represent
    //any tokens and counters, and some way to clearly track life totals.



}
