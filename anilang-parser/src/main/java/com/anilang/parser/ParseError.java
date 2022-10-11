package com.anilang.parser;

import org.antlr.v4.runtime.Recognizer;

/**
 * Represents an error found while parsing.
 *
 * @param recognizer         What parser got the error. From this
 *                           object, you can access the context as well
 *                           as the input stream.
 * @param offendingSymbol    The offending token in the input token
 *                           stream, unless recognizer is a lexer (then it's null).
 * @param line               The line number in the input where the error occurred.
 * @param charPositionInLine The character position within that line where the error occurred.
 * @param msg                The message to emit.
 */
public record ParseError(
        Recognizer<?, ?> recognizer,
        Object offendingSymbol,
        int line,
        int charPositionInLine,
        String msg
) {
}
