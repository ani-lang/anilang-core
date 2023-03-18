/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.Type;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Tests file.
 *
 * @since 0.7.0
 */
@SuppressWarnings("PMD.TooManyMethods")
class TypeResolveListenerTest {

    // TODO 04-03-23 struct member is missed in definition listener
    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    @Disabled
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
}
