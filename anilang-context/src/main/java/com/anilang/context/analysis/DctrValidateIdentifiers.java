/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import java.util.function.BiConsumer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * Run {@link IdentifierValidationListener}.
 *
 * @since 0.7.1
 */
final class DctrValidateIdentifiers implements FileAnalysis {

    /**
     * Decorated.
     */
    private final FileAnalysis origin;

    /**
     * Accept error if any.
     */
    private final BiConsumer<ParserRuleContext, String> consumer;

    /**
     * Ctor.
     *
     * @param origin Decorated.
     * @param consumer Accept error if any.
     */
    DctrValidateIdentifiers(
        final FileAnalysis origin,
        final BiConsumer<ParserRuleContext, String> consumer
    ) {
        this.origin = origin;
        this.consumer = consumer;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierValidationListener(args.context(), this.consumer),
            args.parser().file()
        );
        return args;
    }
}
