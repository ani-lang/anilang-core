/*
 * Property of Opencore
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

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

    /**
     * Error listener that stores each error.
     *
     * @since 0.1.0
     */
    private static final class ListErrorListener extends BaseErrorListener {

        /**
         * The errors.
         */
        private final List<ParseError> errors;

        /**
         * Ctor.
         */
        ListErrorListener() {
            this.errors = new LinkedList<>();
        }

        // @checkstyle ParameterNumberCheck (10 lines)
        @Override
        public void syntaxError(
            final Recognizer<?, ?> recognizer,
            final Object symbol,
            final int line,
            final int column,
            final String msg,
            final RecognitionException exception
        ) {
            this.errors.add(
                new ParseError(
                    line,
                    column,
                    msg
                )
            );
        }

        /**
         * Return errors.
         *
         * @return List of errors.
         */
        public List<ParseError> getErrors() {
            return this.errors;
        }
    }
}
