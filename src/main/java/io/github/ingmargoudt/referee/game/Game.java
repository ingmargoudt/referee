package io.github.ingmargoudt.referee.game;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;

import java.util.Arrays;
import java.util.UUID;

public class Game {

    UUID activePlayer;
    UUID playerWithPriority;
    Battlefield battlefield;
    Player[] players = new Player[2];
    @Getter
    final int startingLife = 20;

    private Stack stack = new Stack(this);

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
        /*
        103.4. Each player draws a number of cards equal to their starting hand size, which is normally seven.
         */
        Arrays.stream(players).forEach(player -> player.drawCard(7));
        Arrays.stream(players).forEach(Player::mulligan);
        activePlayer = players[0].getId();
        playerWithPriority = activePlayer;
        EventBus.report(getPlayer(playerWithPriority).getName() + " gets priority");
        new Turn().run(this);
    }

    public void putOnStack(Spell spell){
        stack.putOnStack(spell);
    }

    public Player getPlayer(UUID controller) {
        return Arrays.stream(players).filter(player -> player.getId().equals(controller)).findFirst().get();
    }

    public Player getActivePlayer(){
        return Arrays.stream(players).filter(player -> player.getId().equals(activePlayer)).findFirst().get();

    }

    public void setPriority(UUID playerId){
        playerWithPriority = playerId;
        EventBus.report(getPlayer(playerWithPriority).getName() + " gets priority");
        applyContinuousEffects();
        checkStateBasedActions();
    }

    private void applyContinuousEffects() {
        EventBus.report("Applying continuous effects");
    }

    private void checkStateBasedActions() {
        EventBus.report("Checking statebased actions");
    }


    public void passPriority(){
        stack.pass(this, playerWithPriority);
        if(stack.allPlayersPassed()){
            return;
        }
        if(players[0].getId().equals(playerWithPriority)){
            setPriority(players[1].getId());
        }
        else{
            setPriority(players[0].getId());
        }


    }

    public Stack getStack(){
        return stack;
    }

    public void moveToBattlefield(Card card) {
        battlefield.add(new Permanent(card));
    }
}
