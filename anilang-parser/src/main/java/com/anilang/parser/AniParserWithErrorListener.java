/*
 * Property of ani-lang project.
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.BaseErrorListener;

/**
 * Add a listener to the {@link AniParser} provided.
 *
 * @since 0.1.0
 */
final class AniParserWithErrorListener implements IoCheckedSupplier<AniParser> {
    /**
     * Parser supplier.
     */
    private final IoCheckedSupplier<AniParser> supplier;

    /**
     * Error listener.
     */
    private final BaseErrorListener listener;

    /**
     * Ctor.
     *
     * @param supplier Parser supplier.
     * @param listener Listener to be added to the parser.
     */
    AniParserWithErrorListener(
        final IoCheckedSupplier<AniParser> supplier,
        final BaseErrorListener listener
    ) {
        this.supplier = supplier;
        this.listener = listener;
    }

    @Override
    public AniParser get() throws IOException {
        final AniParser parser = this.supplier.get();
        parser.addErrorListener(this.listener);
        return parser;
    }
}
