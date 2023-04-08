/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.Type;
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
class TypeDefinitionListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void classTypeDefinition() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-definition/class_definition.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .analyzeIdentifierValidation(new ExceptionBiConsumer())
            .analyzeTypeDefinition()
            .build()
            .run();
        Assertions.assertEquals(
            context.get("1-0").getType(),
            Type.CLASS
        );
    }

    @Test
    void struct() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-definition/struct_definition.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        new FileAnalysisBuilder(context, parser)
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .analyzeIdentifierValidation(new ExceptionBiConsumer())
            .analyzeTypeDefinition()
            .build()
            .run();
        Assertions.assertEquals(
            context.get("1-0").getType(),
            Type.STRUCT
        );
    }
}
