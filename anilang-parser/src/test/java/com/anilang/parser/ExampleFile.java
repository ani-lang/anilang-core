/*
 * Property of Opencore
 */
package com.anilang.parser;

import java.io.InputStream;

/**
 * Read examples from source.
 *
 * @since 0.1.0
 */
public final class ExampleFile {

    /**
     * Example.
     */
    private final ExampleCode example;

    /**
     * Ctor.
     *
     * @param example Example code name.
     */
    public ExampleFile(final ExampleCode example) {
        this.example = example;
    }

    /**
     * Return the input source.
     *
     * @return Input stream of the source.
     */
    public InputStream inputStream() {
        return this.getClass().getResourceAsStream(
            String.format("/com/anilang/parser/%s.ani", this.example.toString())
        );
    }
}
