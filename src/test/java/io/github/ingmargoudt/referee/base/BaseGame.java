package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.cards.g.GrizzlyBears;
import io.github.ingmargoudt.referee.framework.*;
import io.github.ingmargoudt.referee.game.*;
import io.github.ingmargoudt.referee.players.Player;
import org.assertj.core.api.Fail;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseGame {

    protected TestPlayer player1;
    protected TestPlayer player2;
    private TestGame game;
    private TestInputListener inputListener;

    private Library createLibraries(){
        ArrayList<Card>cards = new ArrayList<>();
        for(int i = 0; i<60;i++){
            cards.add(new Card("Mountain"));
        }
        return new Library(cards);
    }


    public BaseGame(){
        inputListener = new TestInputListener();
        game = new TestGame();
        player1 = new TestPlayer("Player 1", game, createLibraries());
        player2 = new TestPlayer("Player 2", game, createLibraries());
        game.addPlayer(player1);
        game.addPlayer(player2);
        EventBus.registerListener(new ConsoleListener());
        InputBus.registerListener(inputListener);

    }

    public void castSpell(int turn, Phase phase, TestPlayer player,Card card){
        player.addAction(new CastSpellAction(turn, phase, card));
    }

    public void stopAt(int turn, Phase phase){
        game.stopAt(turn, phase);
    }

    public void start(){
        game.start();
        boolean ok = true;
        if(player1.hasRemainingActions()){
            EventBus.report(player1.getName() + " has remaining actions");
            ok = false;
        }
        if(player2.hasRemainingActions()){
            EventBus.report(player2.getName() + " has remaining actions");
            ok = false;
        }
        if(!ok){
            Fail.fail("Not all actions executed");
        }
    }

    public void addCard(Zone zone, TestPlayer player, Card card, int i) {
        if(zone == Zone.HAND){
            card.setController(player.getId());
            player.getHand().addCard(card);
        }
    }

    public void assertPermanent(Zone zone, TestPlayer player, Card theCard, int i){
        int amount = 0;
        if(zone == Zone.BATTLEFIELD) {
            for (Permanent permanent : game.getBattlefield().getAll()) {
                if (permanent.getCurrent().getName().equals(theCard.getName()) && permanent.getCurrent().getController().equals(player.getId())) {
                    amount++;
                }

            }
        }
        assertThat(amount).as("Wrong number of %s controlled by %s, expected %s, got %s", theCard.getName(), player.getName(), i, amount).isEqualTo(i);

    }

    public void addCommand(UUID uuid, String command){
        inputListener.addInput(uuid , command);
    }
}
