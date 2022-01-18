package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.game.abilities.ManaAbilityApplier;
import io.github.ingmargoudt.referee.game.objects.Card;
import io.github.ingmargoudt.referee.game.objects.Permanent;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class Test_Rules {

    @Test
    void generateRules() {
        SoftAssertions softAssertions = new SoftAssertions();
        Reflections reflections = new Reflections("io.github.ingmargoudt.referee.cards");
        Set<Class<? extends Card>> allClasses =
                reflections.getSubTypesOf(Card.class);
        allClasses.forEach(theClass ->
        {

            try {
                Card card = (Card) theClass.getDeclaredConstructors()[0].newInstance();
                softAssertions.assertThat(card.getCardtypes().count()).as(card.getName()+ " has no cardtypes").isGreaterThanOrEqualTo(1);
                if(card.isPermanent()) {
                    Permanent permanent = new Permanent(card);
                    ManaAbilityApplier.run(permanent);
                    System.out.println();
                    System.out.println(permanent.getName());
                    System.out.println(permanent.getRule());
                }else {
                    System.out.println(card.getName());
                    System.out.println(card.getRule());
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        softAssertions.assertAll();
    }
}
