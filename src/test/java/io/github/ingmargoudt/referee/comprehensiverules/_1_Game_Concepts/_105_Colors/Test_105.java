package io.github.ingmargoudt.referee.comprehensiverules._1_Game_Concepts._105_Colors;

import io.github.ingmargoudt.referee.game.Color;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class Test_105 {

    //105.1. There are five colors in the Magic game: white, blue, black, red, and green.
    @Test
    void _1051() {
        assertThat(Color.values()).hasSize(5);
        assertThat(Arrays.stream(Color.values()).map(p -> p.toString().toLowerCase()).collect(Collectors.toList())).contains("white");
        assertThat(Arrays.stream(Color.values()).map(p -> p.toString().toLowerCase()).collect(Collectors.toList())).contains("blue");
        assertThat(Arrays.stream(Color.values()).map(p -> p.toString().toLowerCase()).collect(Collectors.toList())).contains("black");
        assertThat(Arrays.stream(Color.values()).map(p -> p.toString().toLowerCase()).collect(Collectors.toList())).contains("red");
        assertThat(Arrays.stream(Color.values()).map(p -> p.toString().toLowerCase()).collect(Collectors.toList())).contains("green");
    }
}
