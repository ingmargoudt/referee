package io.github.ingmargoudt.referee.game.abilities;


import io.github.ingmargoudt.referee.game.Game;
import io.github.ingmargoudt.referee.game.SubType;
import io.github.ingmargoudt.referee.game.cost.TapCost;
import io.github.ingmargoudt.referee.game.objects.Permanent;

public class ManaAbilityApplier {


    public static void run(Game game) {
        for (Permanent permanent : game.getBattlefield().getAll()) {
            processMountain(permanent);
            processSwamp(permanent);
            processPlains(permanent);
        }
    }

    private static void processMountain(Permanent permanent) {
        if (permanent.hasSubType(SubType.MOUNTAIN)) {
            if (!permanent.hasAbility(AddRedManaAbility.class)) {
                permanent.addAbility(new AddRedManaAbility(new TapCost()));
            }
        } else {
            permanent.removeAbility(AddRedManaAbility.class);
        }
    }

    private static void processSwamp(Permanent permanent) {
        if (permanent.hasSubType(SubType.SWAMP)) {
            if (!permanent.hasAbility(AddBlackManaAbility.class)) {
                permanent.addAbility(new AddBlackManaAbility(new TapCost()));
            }
        } else {
            permanent.removeAbility(AddBlackManaAbility.class);
        }
    }

    private static void processPlains(Permanent permanent) {
        if (permanent.hasSubType(SubType.PLAINS)) {
            if (!permanent.hasAbility(AddWhiteManaAbility.class)) {
                permanent.addAbility(new AddWhiteManaAbility(new TapCost()));
            }
        } else {
            permanent.removeAbility(AddWhiteManaAbility.class);
        }
    }
}
