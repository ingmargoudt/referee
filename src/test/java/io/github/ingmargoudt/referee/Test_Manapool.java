package io.github.ingmargoudt.referee;

import io.github.ingmargoudt.referee.game.ManaType;
import io.github.ingmargoudt.referee.game.Manapool;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Test_Manapool {

    @Test
    void addMana(){
        Manapool manapool = new Manapool();
        manapool.add(ManaType.BLACK);
        assertThat(manapool.getPool()).containsKey(ManaType.BLACK);
        assertThat(manapool.getPool().get(ManaType.BLACK)).isEqualTo(1);
    }
}
