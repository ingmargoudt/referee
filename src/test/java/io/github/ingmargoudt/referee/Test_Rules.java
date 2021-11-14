package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.game.objects.Card;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

class Test_Rules {

    @Test
    void generateRules() {
        Reflections reflections = new Reflections("io.github.ingmargoudt.referee.cards");
        Set<Class<? extends Card>> allClasses =
                reflections.getSubTypesOf(Card.class);
        allClasses.forEach(theClass ->
        {

            try {
                Card card = (Card) theClass.getDeclaredConstructors()[0].newInstance();
                System.out.println(card.getName());
                System.out.println(card.getRule());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }
}
