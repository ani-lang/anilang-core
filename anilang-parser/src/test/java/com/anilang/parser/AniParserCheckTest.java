/*
 * Property of Opencore
 */

/*
 * Property of Opencore
 */

/*
 * Property of Opencore
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
class AniParserCheckTest {

    // @checkstyle JavadocMethodCheck (500 lines)

    @Test
    void invalidCharacterAssignation() throws IOException {
        Assertions.assertEquals(
            2,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.INVALID_ASSIGNATION
                ).inputStream()
            ).errors().size(),
            "invalid input return errors"
        );
    }

    @Test
    void validCharacterAssignation() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.LITERAL_ASSIGNATION
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid input"
        );
    }

    @Test
    void validIfStatement() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.IF_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid input"
        );
    }
}
