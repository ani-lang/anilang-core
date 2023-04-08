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
@SuppressWarnings("PMD.TooManyMethods")
class AniParserCheckTest {

    // @checkstyle JavadocMethodCheck (500 lines)
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
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.LITERAL_ASSIGNATION
                ).inputStream()
            ).errors().size(),
            "no errors for valid literal"
        );
    }

    @Test
    void validIfStatement() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.IF_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid if statement"
        );
    }

    @Test
    void validForStatement() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.FOR_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid for loop"
        );
    }

    @Test
    void validDefStatement() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.DEF_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid fun def"
        );
    }

    @Test
    void validFullExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.FULL_SYNTAX
                ).inputStream()
            ).errors().size(),
            "no errors for valid full example"
        );
    }

    @Test
    void validMethodCallExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.METHOD_CALL_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid method call example"
        );
    }

    @Test
    void validWhileExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.WHILE_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid while example"
        );
    }

    @Test
    void validClassExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.CLASS_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid class example"
        );
    }

    @Test
    void validStructExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.STRUCT_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid struct example"
        );
    }

    @Test
    void validCommentExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.COMMENT_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for valid comment example"
        );
    }

    @Test
    void validMatchExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.MATCH_VALID
                ).inputStream()
            ).errors().size(),
            "no errors for atm example"
        );
    }

    @Test
    void useCaseAtmExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.USE_CASE_ATM
                ).inputStream()
            ).errors().size(),
            "no errors for valid match example"
        );
    }

    @Test
    void sqlSelectExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.SELECT_SQL
                ).inputStream()
            ).errors().size(),
            "no errors for valid sql select example"
        );
    }

    @Test
    void sqlUpdateExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.UPDATE_SQL
                ).inputStream()
            ).errors().size(),
            "no errors for valid sql update example"
        );
    }

    @Test
    void sqlDeleteExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.DELETE_SQL
                ).inputStream()
            ).errors().size(),
            "no errors for valid sql delete example"
        );
    }

    @Test
    void sqlInsertExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.INSERT_SQL
                ).inputStream()
            ).errors().size(),
            "no errors for valid sql insert example"
        );
    }

    @Test
    void importDeclarationExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.IMPORT_DECLARATION
                ).inputStream()
            ).errors().size(),
            "no errors for valid import declaration example"
        );
    }

    @Test
    void primitiveTypesExample() throws IOException {
        Assertions.assertEquals(
            0,
            new AniParserCheck(
                new ExampleFile(
                    ExampleCode.PRIMITIVE_TYPES
                ).inputStream()
            ).errors().size(),
            "no errors for valid primitive types example"
        );
    }
}
