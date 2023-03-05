/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.impl.ExceptionBiConsumer;
import com.anilang.context.impl.PositionKey;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import java.util.function.BiConsumer;
import org.antlr.v4.runtime.ParserRuleContext;

public final class IdentifierValidationListener extends AniBaseListener {
    private final AniContext aniContext;
    private final BiConsumer<ParserRuleContext, String> consumer;

    public IdentifierValidationListener(final AniContext aniContext) {
        this(aniContext, new ExceptionBiConsumer());
    }

    public IdentifierValidationListener(final AniContext aniContext,
                                        final BiConsumer<ParserRuleContext, String> consumer) {
        this.aniContext = aniContext;
        this.consumer = consumer;
    }

    @Override
    public void enterType(final AniParser.TypeContext ctx) {
        if (ctx.Identifier() != null) {
            validate(ctx, ctx.Identifier().toString());
        }
    }

    @Override
    public void enterExpression(final AniParser.ExpressionContext ctx) {
        if (ctx.primary() != null && ctx.primary().Identifier() != null) {
            validate(ctx, ctx.primary().Identifier().getText());
        }
    }

    private void validate(final ParserRuleContext ctx, final String identifier) {
        if (!aniContext.contains(new PositionKey(ctx).toString())) {
            consumer.accept(ctx, identifier);
        }
    }
}
