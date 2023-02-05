/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import java.util.function.BiConsumer;
import org.antlr.v4.runtime.ParserRuleContext;

public final class ExceptionBiConsumer implements BiConsumer<ParserRuleContext, String> {
    @Override
    public void accept(final ParserRuleContext ctx, final String identifier) {
        throw new AniParseException(
            String.format(
            "Var '%s' at position [%s:%s] is not defined.",
            identifier,
            ctx.getStart().getLine(),
            ctx.getStart().getCharPositionInLine() + 1
            )
        );
    }
}
