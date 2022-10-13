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
class AniParserSupplierTest {

    // @checkstyle JavadocMethodCheck (500 lines)

    @Test
    void nonNullParser() throws IOException {
        Assertions.assertNotNull(
            new AniParserSupplier(
                new ExampleFile(
                    ExampleCode.LITERAL_ASSIGNATION
                ).inputStream()
            ).get(),
            "by default not null parser"
        );
    }
}
