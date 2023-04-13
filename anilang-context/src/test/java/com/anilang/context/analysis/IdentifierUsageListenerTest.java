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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests file.
 *
 * @since 0.7.0
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.AvoidDuplicateLiterals"})
class IdentifierUsageListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void method() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/func_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(13, context.size());
        Assertions.assertEquals("$file$foo$Person", context.get("5-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$Person",
            context.get(context.get("5-8").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$foo$foo", context.get("7-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$foo",
            context.get(context.get("7-4").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$foo$b", context.get("8-11").getScope().formatted());
        Assertions.assertEquals(
            "$file$foo$b",
            context.get(context.get("8-11").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$bar$a", context.get("12-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$bar$a",
            context.get(context.get("12-8").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$bar$foo", context.get("13-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$foo",
            context.get(context.get("13-4").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void file() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/file_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(12, context.size());
        Assertions.assertEquals("$file$P", context.get("13-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$P",
            context.get(context.get("13-4").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$foo", context.get("14-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$foo",
            context.get(context.get("14-4").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$A", context.get("15-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$A",
            context.get(context.get("15-4").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$a", context.get("16-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("16-4").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$b", context.get("16-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$b",
            context.get(context.get("16-8").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void classUsage() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/class_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$A$foo$S", context.get("6-12").getScope().formatted());
        Assertions.assertEquals(
            "$file$A$S",
            context.get(context.get("6-12").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$B$A", context.get("12-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$A",
            context.get(context.get("12-8").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void ifUsage() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/if_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(5, context.size());
        Assertions.assertEquals("$file$if3-0$a", context.get("3-3").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-3").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$if3-0$if-block4-4$a", context.get("4-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("4-8").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$if3-0$if-block4-4$if5-4$b", context.get("5-7").getScope().formatted());
        Assertions.assertEquals(
            "$file$if3-0$if-block4-4$b",
            context.get(context.get("5-7").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void elseUsage() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/else_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(4, context.size());
        Assertions.assertEquals("$file$if3-0$a", context.get("3-3").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-3").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$if3-0$else-block5-0$a", context.get("6-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("6-8").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void whileUsage() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/while_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$while2-0$a", context.get("2-6").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("2-6").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$while2-0$a", context.get("3-4").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-4").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$while2-0$a", context.get("3-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("3-8").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$while2-0$a", context.get("4-8").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("4-8").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$while2-0$while5-4$c", context.get("5-10").getScope().formatted());
        Assertions.assertEquals(
            "$file$while2-0$c",
            context.get(context.get("5-10").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void forUsage() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/for_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(9, context.size());
        Assertions.assertEquals("$file$for2-0$items", context.get("2-9").getScope().formatted());
        Assertions.assertEquals(
            "$file$items",
            context.get(context.get("2-9").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$for2-0$b", context.get("3-9").getScope().formatted());
        Assertions.assertEquals(
            "$file$for2-0$b",
            context.get(context.get("3-9").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$for2-0$for4-4$c", context.get("4-13").getScope().formatted());
        Assertions.assertEquals(
            "$file$for2-0$c",
            context.get(context.get("4-13").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$for2-0$for4-4$d", context.get("5-12").getScope().formatted());
        Assertions.assertEquals(
            "$file$for2-0$for4-4$d",
            context.get(context.get("5-12").getDeclaration()).getScope().formatted()
        );
    }

    @Test
    void match() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("usage/match_scope.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        Assertions.assertEquals(8, context.size());
        Assertions.assertEquals("$file$match2-0$a", context.get("2-6").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("2-6").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$match2-0$case-expr7-4$a", context.get("7-9").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("7-9").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals("$file$match2-0$case-expr7-4$a", context.get("8-12").getScope().formatted());
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("8-12").getDeclaration()).getScope().formatted()
        );
        Assertions.assertEquals(
            "$file$match2-0$case-default11-4$a",
            context.get("12-12").getScope().formatted()
        );
        Assertions.assertEquals(
            "$file$a",
            context.get(context.get("12-12").getDeclaration()).getScope().formatted()
        );
    }
}
