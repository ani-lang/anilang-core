package com.anilang.parser;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import org.junit.Test;


public class AniParserCheckTest {
    @Test
    public void invalidCharacterAssignation() throws IOException {
        assertThat(
                "invalid assignation is an error",
                new AniParserCheck(
                        new ExampleFile(
                                "invalid_assignation"
                        ).inputStream()
                ).errors().size(),
                is(1)
        );
    }

    @Test
    public void validCharacterAssignation() throws IOException {
        assertThat(
                "valid assignation return empty list of errors",
                new AniParserCheck(
                        new ExampleFile(
                                "literal_assignation"
                        ).inputStream()
                ).errors().size(),
                is(0)
        );
    }
}
