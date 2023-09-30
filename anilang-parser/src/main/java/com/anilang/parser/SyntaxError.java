/*
 * Property of ani-lang project.
 */

package com.anilang.parser;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.RecognitionException;

final class SyntaxError {
    private final RecognitionException exception;

    public SyntaxError(final RecognitionException exception) {
        this.exception = exception;
    }

    String message() {
        if(exception instanceof NoViableAltException) {
            return "Token no esperado.";
        }
        return exception.getMessage();
    }
}
