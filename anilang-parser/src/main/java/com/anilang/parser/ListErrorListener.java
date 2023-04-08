/*
 * Property of ani-lang project.
 */
package com.anilang.parser;

import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Error listener that stores each error.
 *
 * @since 0.1.0
 */
final class ListErrorListener extends BaseErrorListener {

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
