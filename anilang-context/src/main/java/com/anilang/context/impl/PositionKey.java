/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Use the Start position to define a unique key. Every token must have a different position
 * within a file.
 * We use the pair [line-charPosition] with line[1,n) top-bottom and charPosition[0,n) left-right.
 */
public final class PositionKey {
    private final ParserRuleContext ctx;

    public PositionKey(final ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public String toString() {
        return ctx.getStart().getLine() + "-" + ctx.getStart().getCharPositionInLine();
    }
}
