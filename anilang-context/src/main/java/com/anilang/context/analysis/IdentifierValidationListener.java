/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.impl.PositionKey;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import java.util.function.BiConsumer;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * In this phase the collected data for declaration is validated.
 *
 * @since 0.7.0
 */
final class IdentifierValidationListener extends AniBaseListener {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Consumer.
     */
    private final BiConsumer<ParserRuleContext, String> consumer;

    /**
     * Ctor.
     *
     * @param context Context.
     * @param consumer Consumer.
     */
    IdentifierValidationListener(
        final AniContext context,
        final BiConsumer<ParserRuleContext, String> consumer
    ) {
        this.context = context;
        this.consumer = consumer;
    }

    @Override
    public void enterType(final AniParser.TypeContext rule) {
        if (rule.Identifier() != null) {
            this.validate(rule, rule.Identifier().toString());
        }
    }

    @Override
    public void enterExpressionValue(final AniParser.ExpressionValueContext rule) {
        if (rule.primary() != null && rule.primary().Identifier() != null) {
            this.validate(rule, rule.primary().Identifier().getText());
        }
    }

    @Override
    public void enterExpressionInstantiation(final AniParser.ExpressionInstantiationContext rule) {
        this.validate(rule, rule.Identifier().getText());
    }

    /**
     * Validate that the identifier is delcared under the rule's scope.
     *
     * @param rule Rule.
     * @param identifier Identifier.
     */
    private void validate(final ParserRuleContext rule, final String identifier) {
        if (!this.context.contains(new PositionKey(rule).toString())) {
            this.consumer.accept(rule, identifier);
        }
    }
}
