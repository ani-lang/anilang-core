/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.impl.AniParseException;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentifierValidationListenerTest {
    @Test
    void func_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/func_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'Person' at position [1:9] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void file_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/file_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'P' at position [1:5] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void class_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/class_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'A' at position [2:9] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void if_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/if_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'a' at position [1:4] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void else_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/else_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'b' at position [4:9] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void while_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/while_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'a' at position [1:7] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void for_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/for_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'items' at position [1:10] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void match_scope_validation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("validation/match_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Exception exception = Assertions.assertThrows(
            AniParseException.class,
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );

        final String expectedMessage = "Var 'a' at position [1:7] is not defined.";
        final String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void use_case_validation() throws IOException {
        // TODO: 31-01-23 null added to syntax. need tests?
        // TODO: 02-02-23 esta es la fase 1
        final AniParser parser = new AniFile(
            new ExampleFile("validation/use-case-atm.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();

        // TODO: 02-02-23 explicar como fases
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();

        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        // TODO: 31-01-23 sql.select es declarator
        // sql.update es una accion. Corregir esa ambiguedad con una mejor syntaxis
        // por ahora ambas seran acciones para ser consistentes.

        // TODO: 02-02-23 typificar todo
    }
}
