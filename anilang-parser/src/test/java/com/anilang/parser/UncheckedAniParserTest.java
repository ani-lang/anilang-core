/*
 * Property of Opencore
 */
package com.anilang.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for AniParser.
 *
 * @since 0.1.0
 */
class UncheckedAniParserTest {

    // @checkstyle JavadocMethodCheck (500 lines)

    @Test
    void throwErrorIfParserFails() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new UncheckedAniParser(
                new ExampleFile(ExampleCode.INVALID_ASSIGNATION).inputStream()
            ).get().file(),
            "failure throws an exception"
        );
    }
}
