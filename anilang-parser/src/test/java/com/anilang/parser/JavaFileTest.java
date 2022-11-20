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
 * Test.
 *
 * @since 0.2.0
 */
class JavaFileTest {

    // @checkstyle JavadocMethodCheck (500 lines)

    @Test
    void uncheckedParse() {
        Assertions.assertThrows(
            IllegalStateException.class,
            () -> new JavaFile(
                new ExampleFile(ExampleCode.JAVA_INVALID, "example").inputStream()
            ).parse().compilationUnit(),
            "invalid input throw exceptions"
        );
    }

    @Test
    void nonNullParser() throws IOException {
        Assertions.assertNotNull(
            new JavaFile(
                new ExampleFile(ExampleCode.JAVA_VALID, "example").inputStream()
            ).parse(),
            "default parser is not null"
        );
    }

    @Test
    void getListOfErrors() throws IOException {
        Assertions.assertFalse(
            new JavaFile(
                new ExampleFile(ExampleCode.JAVA_INVALID, "example").inputStream()
            ).errors().isEmpty(),
            "invalid input has errors"
        );
    }
}
