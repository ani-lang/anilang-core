package com.anilang.parser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

public class AniParserWithErrorListenerTest {

    @Test
    public void errorListenerAddedToTheParser() throws IOException {
        assertThat(
                "listener is added to the parser",
                new AniParserExec(
                        new AniParserSupplier(
                                new ExampleFile("invalid_assignation").inputStream()
                        ),
                        new BasicBaseErrorListener()
                ).getError(),
                notNullValue()
        );
    }

    private record AniParserExec(AniParserSupplier parserSupplier, BasicBaseErrorListener errorListener) {
        String getError() throws IOException {
            new AniParserWithErrorListener(
                    parserSupplier,
                    errorListener
            ).get().file();
            return errorListener.getError();
        }
    }

    private static final class BasicBaseErrorListener extends BaseErrorListener {

        private String error;

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                                Object offendingSymbol,
                                int line,
                                int charPositionInLine,
                                String msg,
                                RecognitionException e) {
            error = msg;
        }

        public String getError() {
            return error;
        }
    }
}
