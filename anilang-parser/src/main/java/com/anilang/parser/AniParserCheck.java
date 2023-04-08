/*
 * Property of ani-lang project.
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Parse the source and provides a list of errors found.
 *
 * @since 0.1.0
 */
final class AniParserCheck {
    /**
     * The input stream.
     */
    private final InputStream input;

    /**
     * Ctor.
     *
     * @param input Source input stream to parse.
     */
    AniParserCheck(final InputStream input) {
        this.input = input;
    }

    /**
     * Provide a list of errors after parsing the source.
     *
     * @return List of {@link ParseError}.
     * @throws IOException when source read fails.
     */
    public List<ParseError> errors() throws IOException {
        final ListErrorListener listener = new ListErrorListener();
        final AniParser parser = new AniParserWithErrorListener(
            new AniParserSupplier(
                this.input
            ),
            listener
        ).get();
        parser.file();
        return listener.getErrors();
    }
}
