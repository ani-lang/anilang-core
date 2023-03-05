/*
 * Property of Opencore
 */

package com.anilang.context.impl;

/**
 * Custom Exception.
 *
 * @since 0.7.0
 */
public final class AniParseException extends RuntimeException {

    /**
     * Ctor.
     *
     * @param msg Message.
     */
    public AniParseException(final String msg) {
        super(msg);
    }
}
