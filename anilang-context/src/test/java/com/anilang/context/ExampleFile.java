/*
 * Property of Opencore
 */
package com.anilang.context;

import java.io.InputStream;

/**
 * Read examples from source.
 *
 * @since 0.1.0
 */
public final class ExampleFile {


    private final String file;

    /**
     * Ctor.
     *
     * @param file Example code name.
     */
    public ExampleFile(final String file) {
        this.file = file;
    }

    /**
     * Return the input source.
     *
     * @return Input stream of the source.
     */
    public InputStream inputStream() {
        return this.getClass().getResourceAsStream(
            String.format("/com/anilang/context/%s", this.file)
        );
    }
}
