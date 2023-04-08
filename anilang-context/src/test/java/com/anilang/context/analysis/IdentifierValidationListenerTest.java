/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.impl.AniParseException;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.context.impl.ExceptionBiConsumer;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests file.
 *
 * @since 0.7.0
 */
@SuppressWarnings("PMD.TooManyMethods")
class IdentifierValidationListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void func() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/func_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'Person' at position [1:9] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void file() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/file_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'P' at position [1:5] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void classValidation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/class_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'A' at position [2:9] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ifValidation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/if_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'a' at position [1:4] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void elseValidation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/else_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'b' at position [4:9] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void whileValidation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/while_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'a' at position [1:7] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void forValidation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/for_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'items' at position [1:10] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void matchValidation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/match_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        final Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
        final String expected = "Var 'a' at position [1:7] is not defined.";
        final String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void useCaseValidation() throws IOException {
        /* @checkstyle MethodBodyCommentsCheck (10 lines)
         * TODO 31-01-23 null added to syntax. need tests?
         */
        final AniParser parser = new AniFile(
            new ExampleFile("validation/use-case-atm.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        Assertions.assertDoesNotThrow(
            () -> new FileAnalysisBuilder(context, parser)
                .analyzeDeclaration()
                .analyzeUsageBuilder()
                .analyzeIdentifierValidation(new ExceptionBiConsumer())
                .build()
                .run()
        );
    }
}
