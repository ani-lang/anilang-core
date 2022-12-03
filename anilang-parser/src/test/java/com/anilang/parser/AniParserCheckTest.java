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
    /* @checkstyle MethodBodyCommentsCheck (5 lines)
     * TODO actual and expected values are swapped
     *  #46 let's fix the unit tests values.
     */
    @Test
    void invalidCharacterAssignation() throws IOException {
        Assertions.assertEquals(
            1,
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
            "no errors for valid literal"
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
            "no errors for valid if statement"
        );
    }

    @Test
    void validForStatement() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.FOR_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid for loop"
        );
    }

    @Test
    void validDefStatement() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.DEF_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid fun def"
        );
    }

    @Test
    void validFullExample() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.FULL_SYNTAX
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid full example"
        );
    }

    @Test
    void validMethodCallExample() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.METHOD_CALL_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid method call example"
        );
    }

    @Test
    void validWhileExample() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.WHILE_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid while example"
        );
    }

    @Test
    void validClassExample() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.CLASS_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid class example"
        );
    }

    @Test
    void validStructExample() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.STRUCT_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid struct example"
        );
    }

    @Test
    void validCommentExample() throws IOException {
        Assertions.assertEquals(
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.COMMENT_VALID
                ).inputStream()
            ).errors().size(),
            0,
            "no errors for valid comment example"
        );
    }
}
