/*
 * Property of Opencore
 */

package com.anilang.context.analysis;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Run {@link IdentifierUsageListener}.
 *
 * @since 0.7.1
 */
final class DctrUsageAnalysis implements FileAnalysis {

    /**
     * Decorated.
     */
    private final FileAnalysis origin;

    /**
     * Ctor.
     *
     * @param origin Decorated.
     */
    DctrUsageAnalysis(final FileAnalysis origin) {
        this.origin = origin;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(args.context()),
            args.parser().file()
        );
        return args;
    }
}
