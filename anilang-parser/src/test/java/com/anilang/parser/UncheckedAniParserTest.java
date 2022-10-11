package com.anilang.parser;

import java.io.IOException;
import org.junit.Test;

public class UncheckedAniParserTest {

    @Test(expected = IllegalStateException.class)
    public void throwErrorIfParserFails() throws IOException {
        new UncheckedAniParser(
                new ExampleFile("invalid_assignation").inputStream()
        ).get().file();
    }
}
