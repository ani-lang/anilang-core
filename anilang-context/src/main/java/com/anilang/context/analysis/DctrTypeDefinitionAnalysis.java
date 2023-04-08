/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Run {@link TypeDefinitionListener}.
 *
 * @since 0.7.1
 */
final class DctrTypeDefinitionAnalysis implements FileAnalysis {

    /**
     * Decorated.
     */
    private final FileAnalysis origin;

    /**
     * Ctor.
     *
     * @param origin Decorated.
     */
    DctrTypeDefinitionAnalysis(final FileAnalysis origin) {
        this.origin = origin;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(args.context()),
            args.parser().file()
        );
        return args;
    }
}
