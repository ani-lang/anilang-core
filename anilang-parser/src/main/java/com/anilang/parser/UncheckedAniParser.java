package com.anilang.parser;

import static java.lang.String.format;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Read the source and create a parser without checking for {@link IOException}.
 */
final class UncheckedAniParser implements IOCheckedSupplier<AniParser> {
    private final InputStream inputStream;

    /**
     * ctor.
     *
     * @param inputStream source input stream to parse.
     */
    UncheckedAniParser(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return a parser.
     * @throws IOException when input source read fails.
     */
    @Override
    public AniParser get() throws IOException {
        return new AniParserWithErrorListener(
                new AniParserSupplier(
                        inputStream
                ),
                new InternalBaseErrorListener()
        ).get();
    }

    /**
     * Error listener that throws and exception if a parser error is found.
     */
    private static final class InternalBaseErrorListener extends BaseErrorListener {

        private static final String PARSE_ERROR_TEMPLATE = "Failed to parse at [Line:Column] = [%s:%s] caused by %s";

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line,
                                int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            throw new IllegalStateException(format(PARSE_ERROR_TEMPLATE, line, charPositionInLine, msg), e);
        }
    }
}
