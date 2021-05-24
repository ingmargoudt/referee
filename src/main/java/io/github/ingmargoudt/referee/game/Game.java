package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

import java.util.Arrays;

public class Game {

    Battlefield battlefield;
    Player[] players = new Player[2];
    @Getter
    final int startingLife = 20;

    public Game(){
        battlefield = new Battlefield();
    }

    public void addPlayer(Player player){
        for(int i = 0 ; i < players.length ; i++){
            if(players[i] == null){
                players[i] = player;
                return;
            }
        }
    }

    public void gameloop(){
        startTheGame();
    }

    private void startTheGame() {
        /*
        . 103.2
        After the starting player has been determined, each player shuffles their deck so that the cards
           are in a random order. Each player may then shuffle or cut their opponents’ decks. The players’
           decks become their libraries.
         */
        Arrays.stream(players).forEach(Player::shuffle);
        /*
        103.3. Each player begins the game with a starting life total of 20. Some variant games have different
        starting life totals
         */
        Arrays.stream(players).forEach(player -> player.setLife(startingLife));
    }
}
