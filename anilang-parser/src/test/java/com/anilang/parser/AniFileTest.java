/*
 * Property of ani-lang project.
 */
package com.anilang.parser;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test file.
 *
 * @since 0.1.0
 */
class AniFileTest {

    // @checkstyle JavadocMethodCheck (500 lines)

    @Test
    void uncheckedParse() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new AniFile(
                new ExampleFile(ExampleCode.INVALID_ASSIGNATION).inputStream()
            ).parse().file(),
            "invalid input throw exceptions"
        );
    }

    @Test
    void nonNullParser() throws IOException {
        Assertions.assertNotNull(
            new AniFile(
                new ExampleFile(ExampleCode.LITERAL_ASSIGNATION).inputStream()
            ).parse(),
            "default parser is not null"
        );
    }

    @Test
    void getListOfErrors() throws IOException {
        Assertions.assertFalse(
            new AniFile(
                new ExampleFile(ExampleCode.INVALID_ASSIGNATION).inputStream()
            ).errors().isEmpty(),
            "invalid input has errors"
        );
    }
}
