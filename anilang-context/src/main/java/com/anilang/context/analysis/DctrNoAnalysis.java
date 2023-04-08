/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.parser.antlr.AniParser;

/**
 * Used as base analysis. This does not run any validation.
 *
 * @since 0.7.1
 */
final class DctrNoAnalysis implements FileAnalysis {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Parser.
     */
    private final AniParser parser;

    /**
     * Ctor.
     *
     * @param context Context.
     * @param parser Parser.
     */
    DctrNoAnalysis(final AniContext context, final AniParser parser) {
        this.context = context;
        this.parser = parser;
    }

    @Override
    public DctrArgs run() {
        return new DctrArgs(
            this.context,
            this.parser
        );
    }
}
