/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Use the Start position to define a unique key. Every token must have a different position
 * within a file.
 * We use the pair [line-charPosition] with line[1,n) top-bottom and charPosition[0,n) left-right.
 *
 * @since 0.7.0
 */
public final class PositionKey {

    /**
     * Rule.
     */
    private final ParserRuleContext rule;

    /**
     * Ctor.
     *
     * @param rule Rule.
     */
    public PositionKey(final ParserRuleContext rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return String.format(
            "%s-%s",
            this.rule.getStart().getLine(),
            this.rule.getStart().getCharPositionInLine()
        );
    }
}
