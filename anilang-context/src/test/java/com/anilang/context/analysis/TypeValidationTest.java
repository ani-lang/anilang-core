/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.context.impl.ExceptionBiConsumer;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test file.
 *
 * @since 0.7.1
 */
@SuppressWarnings("PMD.TooManyMethods")
class TypeValidationTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void test() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-validation/not_defined_class_property.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            RuntimeException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .analyzeTypeDefinition()
                .analyzeTypeResolve()
                .analyzeTypesValidation(error -> {
                    throw new RuntimeException(error);
                })
                .build()
                .run()
        );
        final String expected = "Cannot resolve type of var 'p3' at [9,1]";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
