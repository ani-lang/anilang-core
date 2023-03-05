/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.PositionKey;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;

public final class TypeDefinitionListener extends AniBaseListener {

    private final AniContext aniContext;

    public TypeDefinitionListener(final AniContext aniContext) {
        this.aniContext = aniContext;
    }

    @Override
    public void enterClassDeclaration(final AniParser.ClassDeclarationContext ctx) {
        asType(ctx, Type.CLASS);
    }

    @Override
    public void enterStructDeclaration(final AniParser.StructDeclarationContext ctx) {
        asType(ctx, Type.STRUCT);
    }

    private void asType(final ParserRuleContext ctx, final Type type) {
        final String key = new PositionKey(ctx).toString();
        if (aniContext.contains(key)) {
            final ContextMetadata contextMetadata = aniContext.get(key);
            contextMetadata.asType(type);
        }
    }
}
