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
    public void enterType(final AniParser.TypeContext rule) {
        if (rule.Identifier() != null) {
            final String identifier = rule.Identifier().getText();
            new LookupParentContext(
                this.context,
                identifier,
                rule
            ).addIfFound();
        }
    }

    @Override
    public void enterExpressionValue(final AniParser.ExpressionValueContext rule) {
        if (rule.primary() != null && rule.primary().Identifier() != null) {
            final String identifier = rule.primary().Identifier().getText();
            new LookupParentContext(
                this.context,
                identifier,
                rule
            ).addIfFound();
        }
    }

    @Override
    public void enterSqlDeclarator(final AniParser.SqlDeclaratorContext rule) {
        if (rule.Identifier() != null) {
            final String identifier = rule.Identifier().getText();
            new LookupParentContext(
                this.context,
                identifier,
                rule
            ).addIfFound();
        }
    }

    @Override
    public void enterVariableDeclaratorId(final AniParser.VariableDeclaratorIdContext rule) {
        new LookupParentContext(
            this.context,
            rule.Identifier().getText(),
            rule
        ).addIfFound();
    }

    @Override
    public void enterExpressionInstantiation(final AniParser.ExpressionInstantiationContext rule) {
        new LookupParentContext(
            this.context,
            rule.Identifier().getText(),
            rule
        ).addIfFound();
    }
}
