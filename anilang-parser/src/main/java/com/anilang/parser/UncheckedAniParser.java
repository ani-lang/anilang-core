/*
 * Property of Opencore
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;

/**
 * Read the source and create a parser without checking for {@link IOException}.
 *
 * @since 0.1.0
 */
final class UncheckedAniParser implements IoCheckedSupplier<AniParser> {

    /**
     * The input source.
     */
    private final InputStream input;

    /**
     * Ctor.
     *
     * @param input Source input stream to parse.
     */
    UncheckedAniParser(final InputStream input) {
        this.input = input;
    }

    @Override
    public AniParser get() throws IOException {
        return new AniParserWithErrorListener(
            new AniParserSupplier(
                this.input
            ),
            new InternalBaseErrorListener()
        ).get();
    }
}
