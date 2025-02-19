package com.github.GeoffreyBoulay.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DepartementPageParserTest {

    @Test
    void testDepartementPageParser() throws IOException {

        List<String> urls = new DepartementPageParser().getCityUrl("https://www.villesetvillagesouilfaitbonvivre.com/vivre-dans-le-59");

        assertThat(urls).contains("/vivre-a-Flers-en-Escrebieux-59128/59234/59");

    }

}