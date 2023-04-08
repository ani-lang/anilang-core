/*
 * Property of Opencore
 */

package com.anilang.context.analysis;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Run {@link IdentifierDeclarationListener}.
 *
 * @since 0.7.1
 */
final class DctrDeclarationAnalysis implements FileAnalysis {

    /**
     * Decorated.
     */
    private final FileAnalysis origin;

    /**
     * Ctor.
     *
     * @param origin Decorated.
     */
    DctrDeclarationAnalysis(final FileAnalysis origin) {
        this.origin = origin;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(args.context()),
            args.parser().file()
        );
        return args;
    }
}
