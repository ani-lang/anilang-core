/*
 * Property of Opencore
 */
package com.anilang.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Error listener that throws and exception if a parser error is found.
 *
 * @since 0.1.0
 */
final class InternalBaseErrorListener extends BaseErrorListener {

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
