/*
 * Property of ani-lang project.
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
    private final String example;

    /**
     * File extension.
     */
    private final String extension;

    /**
     * Ctor.
     *
     * @param example Example code name.
     */
    public ExampleFile(final ExampleCode example) {
        this(example.toString(), "ani");
    }

    /**
     * Ctor.
     *
     * @param example File name.
     * @param extension Extension name.
     */
    public ExampleFile(final String example, final String extension) {
        this.example = example;
        this.extension = extension;
    }

    /**
     * Return the input source.
     *
     * @return Input stream of the source.
     */
    public InputStream inputStream() {
        return this.getClass().getResourceAsStream(
            String.format("/com/anilang/parser/%s.%s", this.example, this.extension)
        );
    }
}
