package io.github.ingmargoudt.referee.base;

import io.github.ingmargoudt.referee.game.Phase;
import io.github.ingmargoudt.referee.game.abilities.ActivatedAbility;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.MagicObject;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import io.github.ingmargoudt.referee.game.properties.Targetable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ActivateAbilityAction extends TestPlayerAction {

    @Getter
    private ActivatedAbility activatedAbility;

    @Getter
    private MagicObject source;


    private List<Targetable> targets = new ArrayList<>();


    public ActivateAbilityAction(int turn, Phase phase, ActivatedAbility activatedAbility, MagicObject source) {
        super(turn, phase);
        this.activatedAbility = activatedAbility;
        this.source = source;
    }

    public ActivateAbilityAction(int turn, Phase phase, ActivatedAbility activatedAbility, MagicObject source, Targetable targetable) {
        super(turn, phase);
        this.activatedAbility = activatedAbility;
        this.source = source;
        targets.add(targetable);
    }



    public String toString() {
        return "Activating " + activatedAbility.getClass().getSimpleName();
    }

    @Override
    public void execute(TestPlayer player) {
        player.activateAbility(activatedAbility, source);
    }

    public Targetable consumeTarget() {
        return targets.remove(0);
    }
}
