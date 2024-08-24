package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.framework.EventBus;
import io.github.ingmargoudt.referee.game.Step;
import io.github.ingmargoudt.referee.game.objects.Card;
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
    private TestPlayerAction currentAction;
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
            currentAction = action;

            if(action instanceof AttackAction && action.turn == gameReference.getTurnNumber() && gameReference.getCurrentStep() == Step.DECLARE_ATTACKERS){
                action.execute(this);
                playerActionIterator.remove();
                return;
            }
            if(action instanceof BlockAction && action.turn == gameReference.getTurnNumber()&& gameReference.getCurrentStep() == Step.DECLARE_BLOCKERS){
                action.execute(this);
                playerActionIterator.remove();
                return;
            }
            if ((action.phase == gameReference.getCurrentPhase() || action.step == gameReference.getCurrentStep()) && action.turn == gameReference.getTurnNumber()) {
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
                if(action instanceof ActivateAbilityAction && gameReference.isPlayable(this, ((ActivateAbilityAction) action).getActivatedAbility(), ((ActivateAbilityAction) action).getSource())){
                    action.execute(this);
                    playerActionIterator.remove();
                    return;
                }
            }
        }
        passPriority();

    }

    @Override
    public Targetable chooseTarget(List<Targetable> validTargets) {
        if (currentAction instanceof CastSpellAction) {
            return ((CastSpellAction) currentAction).consumeTarget();
        }
        if(currentAction instanceof ActivateAbilityAction){
            return ((ActivateAbilityAction) currentAction).consumeTarget();
        }
        return Fail.fail("Current action has no (legal) target");
    }

    @Override
    public String choosesOption(List<String> options) {
        return this.options.remove(0);
    }

    public void declareBlocker(Card blocker, Card toBlock) {
        gameReference.getBattlefield().findPermanent(blocker).ifPresent(b -> {
            gameReference.getBattlefield().findPermanent(toBlock).ifPresent(p -> p.getBlockers().add(b));
            EventBus.report(blocker.getName() + " is declared to block "+toBlock.getName());
        });
    }

    public void declareAttacker(Card card){
        gameReference.getBattlefield().findPermanent(card).ifPresent(this::declareAttacker);
    }
}
