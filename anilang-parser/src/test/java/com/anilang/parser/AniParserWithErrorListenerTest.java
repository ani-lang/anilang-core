/*
 * Property of ani-lang project.
 */
package com.anilang.parser;

import java.io.IOException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test file.
 *
 * @since 0.1.0
 */
class AniParserWithErrorListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)

    @Test
    void errorListenerAddedToTheParser() throws IOException {
        Assertions.assertNotNull(
            new AniParserExec(
                new AniParserSupplier(
                    new ExampleFile(ExampleCode.INVALID_ASSIGNATION).inputStream()
                ),
                new BasicBaseErrorListener()
            ).getError(),
            "add a error listener"
        );
    }

    /**
     * Trigger the parser's tree build.
     *
     * @since 0.1.0
     */
    private static final class AniParserExec {

        /**
         * Parser supplier.
         */
        private final AniParserSupplier supplier;

        /**
         * Error listener.
         */
        private final BasicBaseErrorListener listener;

        /**
         * Ctor.
         *
         * @param supplier Parser supplier.
         * @param listener Error listener.
         */
        private AniParserExec(
            final AniParserSupplier supplier,
            final BasicBaseErrorListener listener
        ) {
            this.supplier = supplier;
            this.listener = listener;
        }

        /**
         * Return the caught error if any.
         *
         * @return Error.
         * @throws IOException when the read of the input fails.
         */
        ParseError getError() throws IOException {
            new AniParserWithErrorListener(
                this.supplier,
                this.listener
            ).get().file();
            return this.listener.getError();
        }
    }

    /**
     * Error listener that catches an error.
     *
     * @since 0.1.0
     */
    private static final class BasicBaseErrorListener extends BaseErrorListener {

        /**
         * Error.
         */
        private ParseError error;

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
            this.error = new ParseError(
                line,
                column,
                msg
            );
        }

        /**
         * Return the error.
         *
         * @return Parser error.
         */
        public ParseError getError() {
            return this.error;
        }
    }
}
