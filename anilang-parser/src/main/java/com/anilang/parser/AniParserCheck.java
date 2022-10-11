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
 */
final class AniParserCheck {
    private final InputStream inputStream;

    /**
     * ctor.
     *
     * @param inputStream source input stream to parse.
     */
    AniParserCheck(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Provide a list of errors after parsing the source.
     *
     * @return list of {@link ParseError}.
     * @throws IOException when source read fails.
     */
    public List<ParseError> errors() throws IOException {
        final InternalBaseErrorListener internalBaseErrorListener = new InternalBaseErrorListener();
        final AniParser aniParser = new AniParserWithErrorListener(
                new AniParserSupplier(
                        inputStream
                ),
                internalBaseErrorListener
        ).get();
        aniParser.file();
        return internalBaseErrorListener.getErrors();
    }

    /**
     * Error listener that stores each error.
     */
    private static final class InternalBaseErrorListener extends BaseErrorListener {

        private final List<ParseError> parseErrors;

        public InternalBaseErrorListener() {
            this.parseErrors = new LinkedList<>();
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line,
                                int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            parseErrors.add(
                    new ParseError(
                            recognizer,
                            offendingSymbol,
                            line,
                            charPositionInLine,
                            msg
                    )
            );
        }

        public List<ParseError> getErrors() {
            return parseErrors;
        }
    }
}
