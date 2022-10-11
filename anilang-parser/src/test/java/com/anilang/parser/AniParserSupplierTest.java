package com.anilang.parser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import org.junit.Test;

public class AniParserSupplierTest {
    @Test
    public void nonNullParser() throws IOException {
        assertThat(
                "return a non null parser",
                new AniParserSupplier(
                        new ExampleFile(
                                "literal_assignation"
                        ).inputStream()
                ).get(),
                notNullValue()
        );
    }
}
