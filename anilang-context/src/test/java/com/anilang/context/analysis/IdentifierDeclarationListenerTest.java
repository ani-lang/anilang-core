/*
 * Property of Opencore
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests file.
 *
 * @since 0.7.0
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.AvoidDuplicateLiterals"})
class IdentifierDeclarationListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void method() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/func_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(6, context.size());
        Assertions.assertEquals("$file$foo", context.get("1-0").getParents());
        Assertions.assertEquals("$file$foo$a", context.get("2-4").getParents());
        Assertions.assertEquals("$file$foo$b", context.get("3-4").getParents());
        Assertions.assertEquals("$file$bar", context.get("7-0").getParents());
        Assertions.assertEquals("$file$bar$a", context.get("7-15").getParents());
        Assertions.assertEquals("$file$bar$b", context.get("8-4").getParents());
    }

    @Test
    void file() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/file_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(6, context.size());
        Assertions.assertEquals("$file$a", context.get("1-0").getParents());
        Assertions.assertEquals("$file$b", context.get("2-0").getParents());
        Assertions.assertEquals("$file$c", context.get("3-0").getParents());
        Assertions.assertEquals("$file$P", context.get("5-0").getParents());
        Assertions.assertEquals("$file$foo", context.get("9-0").getParents());
        Assertions.assertEquals("$file$A", context.get("13-0").getParents());
    }

    @Test
    void classDeclaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/class_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(6, context.size());
        Assertions.assertEquals("$file$A", context.get("1-0").getParents());
        Assertions.assertEquals("$file$A$a", context.get("2-4").getParents());
        Assertions.assertEquals("$file$A$foo", context.get("3-4").getParents());
        Assertions.assertEquals("$file$A$foo$b", context.get("3-16").getParents());
        Assertions.assertEquals("$file$B", context.get("8-0").getParents());
        Assertions.assertEquals("$file$B$c", context.get("9-4").getParents());
    }

    @Test
    void ifDeclaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/if_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(4, context.size());
        Assertions.assertEquals("$file$if1-0$if-block2-4$b", context.get("2-4").getParents());
        Assertions.assertEquals("$file$if5-0$if-block6-4$c", context.get("6-4").getParents());
        Assertions.assertEquals(
            "$file$if5-0$if-block6-4$if7-4$if-block8-8$d",
            context.get("8-8").getParents()
        );
        Assertions.assertEquals(
            "$file$if5-0$if-block6-4$if11-4$if-block12-8$h",
            context.get("12-8").getParents()
        );
    }

    @Test
    void elseDeclaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/else_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(2, context.size());
        Assertions.assertEquals("$file$if1-0$if-block2-4$b", context.get("2-4").getParents());
        Assertions.assertEquals("$file$if1-0$else-block3-0$d", context.get("4-4").getParents());
    }

    @Test
    void whileDeclaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/while_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(4, context.size());
        Assertions.assertEquals("$file$while1-0$b", context.get("2-4").getParents());
        Assertions.assertEquals("$file$while5-0$c", context.get("6-4").getParents());
        Assertions.assertEquals("$file$while5-0$while7-4$d", context.get("8-8").getParents());
        Assertions.assertEquals("$file$while5-0$while11-4$h", context.get("12-8").getParents());
    }

    @Test
    void forDeclaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/for_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$for1-0$a", context.get("1-4").getParents());
        Assertions.assertEquals("$file$for1-0$b", context.get("2-4").getParents());
        Assertions.assertEquals("$file$for5-0$b", context.get("5-4").getParents());
        Assertions.assertEquals("$file$for5-0$c", context.get("6-4").getParents());
        Assertions.assertEquals("$file$for5-0$for7-4$d", context.get("7-8").getParents());
        Assertions.assertEquals("$file$for5-0$for7-4$e", context.get("8-8").getParents());
        Assertions.assertEquals("$file$for5-0$for11-4$f", context.get("11-8").getParents());
        Assertions.assertEquals("$file$for5-0$for11-4$h", context.get("12-8").getParents());
    }

    @Test
    void match() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("declaration/match_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        Assertions.assertEquals(4, context.size());
        Assertions.assertEquals("$file$match1-0$case-expr2-4$b", context.get("3-8").getParents());
        Assertions.assertEquals("$file$match1-0$case-expr6-4$c", context.get("7-8").getParents());
        /* @checkstyle MethodBodyCommentsCheck (10 lines)
         * TODO 25-01-23 remove rule |   'case' Identifier scriptBlock #identifierLabelCase.
         * Expression is an identifier as well
         */
        Assertions.assertEquals("$file$match1-0$case-expr10-4$d", context.get("11-8").getParents());
        Assertions.assertEquals(
            "$file$match1-0$case-default14-4$e",
            context.get("15-8").getParents()
        );
    }
}
