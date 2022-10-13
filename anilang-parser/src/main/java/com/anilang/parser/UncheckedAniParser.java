/*
 * Property of Opencore
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

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

    /**
     * Error listener that throws and exception if a parser error is found.
     *
     * @since 0.1.0
     */
    private static final class InternalBaseErrorListener extends BaseErrorListener {

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
            final String template = "Failed to parse at [Line:Column] = [%s:%s] caused by %s";
            throw new IllegalStateException(String.format(template, line, column, msg), exception);
        }
    }
}
