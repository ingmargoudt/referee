package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.properties.Targetable;
import io.github.ingmargoudt.referee.game.zones.Hand;
import io.github.ingmargoudt.referee.game.zones.Library;
import io.github.ingmargoudt.referee.players.Player;
import lombok.Getter;
import org.assertj.core.api.Fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestPlayer extends Player {


    @Getter
    private List<TestPlayerAction> actions = new ArrayList<>();
    private TestPlayerAction currenAction;
    private List<String> options = new ArrayList<>();


    public TestPlayer(String name, TestGame game, Library library) {
        super(name, game, library);
    }

    void addAction(TestPlayerAction playerAction) {
        actions.add(playerAction);
    }

    void addOption(String option) {
        options.add(option);
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public void mulligan() {

    }

    @Override
    public void shuffle() {

    }


    public boolean hasRemainingActions() {
        return !actions.isEmpty();
    }


    @Override
    public void doAction() {
        Iterator<TestPlayerAction> playerActionIterator = actions.listIterator();
        while (playerActionIterator.hasNext()) {
            TestPlayerAction action = playerActionIterator.next();
            currenAction = action;
            if (action.phase == gameReference.getCurrentPhase() && action.turn == gameReference.getTurnNumber()) {
                if (action instanceof CastSpellAction && gameReference.isPlayable(this, ((CastSpellAction) action).getCard())) {
                    action.execute(this);
                    playerActionIterator.remove();
                    return;
                }
                if (action instanceof PlayLandAction && gameReference.isPlayable(this, ((PlayLandAction) action).getCard())) {
                    action.execute(this);
                    playerActionIterator.remove();
                    return;
                }
                if (action instanceof TestSetLifeAction) {
                    action.execute(this);
                    playerActionIterator.remove();
                    return;
                }
                if(action instanceof ActivateAbilityAction){
                    action.execute(this);
                    playerActionIterator.remove();
                    return;
                }
            }
            ;
        }
        passPriority();

    }

    @Override
    public Targetable chooseTarget(List<Targetable> validTargets) {
        if (currenAction instanceof CastSpellAction) {
            return ((CastSpellAction) currenAction).consumeTarget();
        }
        return Fail.fail("Current action has no target");
    }

    @Override
    public String choosesOption(List<String> options) {
        return this.options.remove(0);
    }
}
