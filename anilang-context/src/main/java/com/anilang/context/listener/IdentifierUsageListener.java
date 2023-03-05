/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.impl.LookupParentContext;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;

/**
 * Listen to rules that use identifiers and link them to its declaration if exists.
 * It's a top-bottom lookup throughout the parent contexts.
 *
 * @since 0.7.0
 */
public final class IdentifierUsageListener extends AniBaseListener {

    /**
     * The context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param context Context.
     */
    public IdentifierUsageListener(final AniContext context) {
        this.context = context;
    }

    @Override
    public void enterType(final AniParser.TypeContext ctx) {
        if (ctx.Identifier() != null) {
            final String identifier = ctx.Identifier().getText();
            new LookupParentContext(
                this.context,
                identifier,
                ctx
            ).addIfFound();
        }
    }

    @Override
    public void enterExpression(final AniParser.ExpressionContext ctx) {
        if (ctx.primary() != null && ctx.primary().Identifier() != null) {
            final String identifier = ctx.primary().Identifier().getText();
            new LookupParentContext(
                this.context,
                identifier,
                ctx
            ).addIfFound();
        }
    }

    @Override
    public void enterSqlDeclarator(final AniParser.SqlDeclaratorContext ctx) {
        if (ctx.Identifier() != null) {
            final String identifier = ctx.Identifier().getText();
            new LookupParentContext(
                this.context,
                identifier,
                ctx
            ).addIfFound();
        }
    }

    @Override
    public void enterVariableDeclaratorId(final AniParser.VariableDeclaratorIdContext ctx) {
        new LookupParentContext(
            this.context,
            ctx.Identifier().getText(),
            ctx
        ).addIfFound();
    }
}
