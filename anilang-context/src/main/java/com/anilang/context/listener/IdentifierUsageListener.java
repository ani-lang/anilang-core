/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.impl.ContextToKeys;
import com.anilang.context.impl.LookupExistingContext;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import java.util.Map;

/**
 * Listen to rules that use identifiers and link them to its declaration if exists.
 * It's a top-bottom lookup throughout the parent contexts.
 */
public final class IdentifierUsageListener extends AniBaseListener {

    private final AniContext aniContext;
    private final Map<String, String> contextToKeys;

    public IdentifierUsageListener(final AniContext aniContext) {
        this.aniContext = aniContext;
        this.contextToKeys = new ContextToKeys(aniContext).asMap();
    }

    @Override
    public void enterType(final AniParser.TypeContext ctx) {
        if (ctx.Identifier() != null) {
            final String identifier = ctx.Identifier().getText();
            new LookupExistingContext(
                contextToKeys,
                aniContext,
                identifier,
                ctx
            ).addIfFound();
        }
    }

    @Override
    public void enterExpression(final AniParser.ExpressionContext ctx) {
        if (ctx.primary() != null && ctx.primary().Identifier() != null) {
            final String identifier = ctx.primary().Identifier().getText();
            new LookupExistingContext(
                contextToKeys,
                aniContext,
                identifier,
                ctx
            ).addIfFound();
        }
    }
}
