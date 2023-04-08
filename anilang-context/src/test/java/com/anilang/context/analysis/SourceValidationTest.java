/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test file.
 *
 * @since 0.7.1
 */
@SuppressWarnings("PMD.TooManyMethods")
class SourceValidationTest {
    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void noExtension() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("source-validation/no_extension").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            RuntimeException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeSourceValidation(
                    Paths.get(
                        "/com/anilang/context/source-validation/no_extension"
                    ),
                    error -> {
                        throw new RuntimeException(error);
                    }
                )
                .build()
                .run()
        );
        final String expected = "File extension not recognized: no_extension";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void wrongExtension() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("source-validation/wrong_extension.wrong").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            RuntimeException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeSourceValidation(
                    Paths.get(
                        "/com/anilang/context/source-validation/wrong_extension.wrong"
                    ),
                    error -> {
                        throw new RuntimeException(error);
                    }
                )
                .build()
                .run()
        );
        final String expected = "File extension not recognized: wrong_extension.wrong";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void noError() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("source-validation/valid.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        Assertions.assertDoesNotThrow(
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeSourceValidation(
                    Paths.get(
                        "/com/anilang/context/source-validation/valid.ani"
                    ),
                    error -> {
                        throw new RuntimeException(error);
                    }
                )
                .build()
                .run()
        );
    }
}
