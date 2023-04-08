/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.parser.antlr.AniParser;

/**
 * Args required for analysis.
 *
 * @since 0.7.1
 */
final class DctrArgs {

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
    DctrArgs(final AniContext context, final AniParser parser) {
        this.context = context;
        this.parser = parser;
    }

    /**
     * The parser pointing to the root.
     *
     * @return Parser.
     */
    AniParser parser() {
        this.parser.reset();
        return this.parser;
    }

    /**
     * @return Context.
     */
    AniContext context() {
        return this.context;
    }
}
