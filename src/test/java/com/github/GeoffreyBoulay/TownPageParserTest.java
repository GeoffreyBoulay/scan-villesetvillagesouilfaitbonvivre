package com.github.GeoffreyBoulay;


import com.github.GeoffreyBoulay.model.Position;
import com.github.GeoffreyBoulay.model.TownInfo;
import com.github.GeoffreyBoulay.parser.TownPageParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TownPageParserTest {

    @Test
    void testPhalempin() throws Exception {

        TownInfo townInfo = new TownPageParser().parse("https://www.villesetvillagesouilfaitbonvivre.com/vivre-a-Phalempin-59133/59462/59");

        assertThat(townInfo.label()).isEqualTo("Phalempin");
        assertThat(townInfo.national()).isEqualTo(new Position("Position nationale", 1850,34795));
        assertThat(townInfo.departemental()).isEqualTo(new Position("Position départementale",53,648));
        assertThat(townInfo.nationalCategory()).isEqualTo(new Position("Position parmi les communes de 3500 - 5000 habitants en France",186,969));
        assertThat(townInfo.departementalCategory()).isEqualTo(new Position("Position parmi les communes de 3500 - 5000 habitants dans le département", 1,44));

    }

}