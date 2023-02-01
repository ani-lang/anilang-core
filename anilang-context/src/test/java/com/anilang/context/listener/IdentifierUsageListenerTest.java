/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentifierUsageListenerTest {
    @Test
    void method_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/func_scope.ani").inputStream()
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

        Assertions.assertEquals(12, context.size());
        Assertions.assertEquals("$file$foo$Person", context.get("5-8").getParents());
        Assertions.assertEquals(
            "$file$Person",
            context.get(context.get("5-8").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$foo$foo", context.get("7-4").getParents());
        Assertions.assertEquals(
            "$file$foo",
            context.get(context.get("7-4").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$foo$b", context.get("8-11").getParents());
        Assertions.assertEquals(
            "$file$foo$b",
            context.get(context.get("8-11").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$bar$a", context.get("12-8").getParents());
        Assertions.assertEquals(
            "$file$bar$a",
            context.get(context.get("12-8").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$bar$foo", context.get("13-4").getParents());
        Assertions.assertEquals(
            "$file$foo",
            context.get(context.get("13-4").getDeclarationKey()).getParents()
        );
    }

    @Test
    void file_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/file_scope.ani").inputStream()
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

        Assertions.assertEquals(12, context.size());
        Assertions.assertEquals("$file$P", context.get("13-4").getParents());
        Assertions.assertEquals(
            "$file$P",
            context.get(context.get("13-4").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$foo", context.get("14-4").getParents());
        Assertions.assertEquals(
            "$file$foo",
            context.get(context.get("14-4").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$A", context.get("15-4").getParents());
        Assertions.assertEquals(
            "$file$A",
            context.get(context.get("15-4").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$a", context.get("16-4").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("16-4").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$b", context.get("16-8").getParents());
        Assertions.assertEquals(
            "$file$b",
            context.get(context.get("16-8").getDeclarationKey()).getParents()
        );
    }

    @Test
    void class_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/class_scope.ani").inputStream()
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

        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$A$foo$S", context.get("6-12").getParents());
        Assertions.assertEquals(
            "$file$A$S",
            context.get(context.get("6-12").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$B$A", context.get("12-8").getParents());
        Assertions.assertEquals(
            "$file$A",
            context.get(context.get("12-8").getDeclarationKey()).getParents()
        );
    }

    @Test
    void if_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/if_scope.ani").inputStream()
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

        Assertions.assertEquals(5, context.size());
        Assertions.assertEquals("$file$if3-0$a", context.get("3-3").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-3").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$if3-0$if-block4-4$a", context.get("4-8").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("4-8").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$if3-0$if-block4-4$if5-4$b", context.get("5-7").getParents());
        Assertions.assertEquals(
            "$file$if3-0$if-block4-4$b",
            context.get(context.get("5-7").getDeclarationKey()).getParents()
        );
    }

    @Test
    void else_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/else_scope.ani").inputStream()
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

        Assertions.assertEquals(4, context.size());
        Assertions.assertEquals("$file$if3-0$a", context.get("3-3").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-3").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$if3-0$else-block5-0$a", context.get("6-8").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("6-8").getDeclarationKey()).getParents()
        );
    }

    @Test
    void while_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/while_scope.ani").inputStream()
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

        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$while2-0$a", context.get("2-6").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("2-6").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$while2-0$a", context.get("3-4").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-4").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$while2-0$a", context.get("3-8").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-8").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$while2-0$a", context.get("4-8").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("4-8").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$while2-0$while5-4$c", context.get("5-10").getParents());
        Assertions.assertEquals(
            "$file$while2-0$c",
            context.get(context.get("5-10").getDeclarationKey()).getParents()
        );
    }

    @Test
    void for_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/for_scope.ani").inputStream()
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

        Assertions.assertEquals(9, context.size());
        Assertions.assertEquals("$file$for2-0$items", context.get("2-9").getParents());
        Assertions.assertEquals(
            "$file$items",
            context.get(context.get("2-9").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$for2-0$b", context.get("3-9").getParents());
        Assertions.assertEquals(
            "$file$for2-0$b",
            context.get(context.get("3-9").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$for2-0$for4-4$c", context.get("4-13").getParents());
        Assertions.assertEquals(
            "$file$for2-0$c",
            context.get(context.get("4-13").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$for2-0$for4-4$d", context.get("5-12").getParents());
        Assertions.assertEquals(
            "$file$for2-0$for4-4$d",
            context.get(context.get("5-12").getDeclarationKey()).getParents()
        );
    }

    @Test
    void match_usage_declaration() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/match_scope.ani").inputStream()
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

        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$match2-0$a", context.get("2-6").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("2-6").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$match2-0$case-expr7-4$a", context.get("7-9").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("7-9").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals("$file$match2-0$case-expr7-4$a", context.get("8-12").getParents());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("8-12").getDeclarationKey()).getParents()
        );
        Assertions.assertEquals(
            "$file$match2-0$case-default11-4$a",
            context.get("12-12").getParents()
        );
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("12-12").getDeclarationKey()).getParents()
        );
    }
}
