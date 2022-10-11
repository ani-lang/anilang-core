package com.anilang.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import org.junit.Test;

public class AniFileTest {

    @Test(expected = IllegalStateException.class)
    public void uncheckedParse() throws IOException {
        new AniFile(
                new ExampleFile("invalid_assignation").inputStream()
        ).parse().file();
    }

    @Test
    public void nonNullParser() throws IOException {
        assertThat(
                "parser is not null",
                new AniFile(
                        new ExampleFile("literal_assignation").inputStream()
                ).parse(),
                notNullValue()
        );
    }

    @Test
    public void getListOfErrors() throws IOException {
        assertThat(
                "return list of errors",
                new AniFile(
                        new ExampleFile("invalid_assignation").inputStream()
                ).errors().isEmpty(),
                is(false)
        );
    }
}
