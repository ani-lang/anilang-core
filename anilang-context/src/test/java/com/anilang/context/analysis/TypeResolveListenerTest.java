/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.Type;
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
@SuppressWarnings("PMD.TooManyMethods")
class TypeResolveListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void structMember() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-resolve/struct_member.ani").inputStream()
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
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeResolveListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            context.get("2-12").getType(),
            Type.BOOLEAN
        );
    }

    @Test
    void funcParams() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-resolve/func_params.ani").inputStream()
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
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeResolveListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            Type.BOOLEAN,
            context.get("1-14").getType()
        );
    }

    @Test
    void instantiation() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-resolve/instantiation.ani").inputStream()
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
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeResolveListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            Type.INSTANCE,
            context.get("5-0").getType()
        );
        Assertions.assertEquals(
            Type.INSTANCE,
            context.get("5-4").getType()
        );
        Assertions.assertEquals(
            "1-0",
            context.get("5-0").getTypeReferenceKey().orElse("")
        );
    }

    @Test
    void propertyFromClass() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-resolve/property_from_class.ani").inputStream()
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
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeResolveListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            Type.INT,
            context.get("6-4").getType()
        );
        Assertions.assertEquals(
            Type.STRING,
            context.get("7-4").getType()
        );
        Assertions.assertEquals(
            Type.BOOLEAN,
            context.get("8-4").getType()
        );
        Assertions.assertEquals(
            Type.INSTANCE,
            context.get("9-4").getType()
        );
        Assertions.assertEquals(
            Type.INSTANCE,
            context.get("12-0").getType()
        );
        Assertions.assertEquals(
            Type.INT,
            context.get("13-0").getType()
        );
        Assertions.assertEquals(
            Type.STRING,
            context.get("14-0").getType()
        );
        Assertions.assertEquals(
            Type.BOOLEAN,
            context.get("15-0").getType()
        );
        Assertions.assertEquals(
            Type.INSTANCE,
            context.get("16-0").getType()
        );
    }

    @Test
    void functionDefinition() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-resolve/func_definition.ani").inputStream()
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
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeResolveListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            Type.INT,
            context.get("1-0").getType()
        );
        Assertions.assertEquals(
            Type.INT,
            context.get("2-11").getType()
        );
    }

    @Test
    void functionInExpression() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-resolve/func_in_expression.ani").inputStream()
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
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeResolveListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            Type.UNKNOWN,
            context.get("18-0").getType()
        );
        Assertions.assertEquals(
            Type.STRING,
            context.get("19-0").getType()
        );
        Assertions.assertEquals(
            Type.INSTANCE,
            context.get("20-0").getType()
        );
    }
}
