/*
 * Property of Opencore
 */

/*
 * Property of Opencore
 */
package com.anilang.parser;

/**
 * Parse error.
 *
 * @since 0.1.0
 */
public final class ParseError {
    /**
     * The error.
     */
    private final String message;

    /**
     * Ctor.
     *
     * @param row The row position.
     * @param column The column position.
     * @param message The error message.
     */
    public ParseError(final int row, final int column, final String message) {
        this.message = String.format(
            "[%s:%s]: %s",
            row,
            column,
            message
        );
    }

    /**
     * Return the error.
     *
     * @return Error message.
     */
    public String getError() {
        return this.message;
    }
}
