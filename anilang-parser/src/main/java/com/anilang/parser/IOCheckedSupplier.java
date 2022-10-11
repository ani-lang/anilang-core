package com.anilang.parser;

import java.io.IOException;

/**
 * Supplier that checks for {@link IOException}.
 *
 * @param <T> type to be supplied.
 */
@FunctionalInterface
public interface IOCheckedSupplier<T> {
    T get() throws IOException;
}
