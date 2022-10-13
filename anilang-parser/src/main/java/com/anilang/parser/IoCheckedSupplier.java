/*
 * Property of Opencore
 */
package com.anilang.parser;

import java.io.IOException;

/**
 * Supplier that checks for {@link IOException}.
 *
 * @param <T> type to be supplied.
 * @since 0.1.0
 */
@FunctionalInterface
public interface IoCheckedSupplier<T> {
    /**
     * Return the Object supplied.
     *
     * @return Object supplied.
     * @throws IOException Error happens when supplying.
     */
    T get() throws IOException;
}
