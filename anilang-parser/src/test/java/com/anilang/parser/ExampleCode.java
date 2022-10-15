/*
 * Property of Opencore
 */
package com.anilang.parser;

/**
 * Example file names.
 *
 * @since 0.1.0
 */
public enum ExampleCode {

    /**
     * An invalid example.
     */
    INVALID_ASSIGNATION("invalid_assignation"),

    /**
     * A valid example.
     */
    LITERAL_ASSIGNATION("literal_assignation"),

    /**
     * Invalid Java example.
     */
    JAVA_INVALID("invalid_java"),

    /**
     * Invalid Java example.
     */
    JAVA_VALID("valid_java");

    /**
     * File name.
     */
    private final String name;

    /**
     * Ctor.
     *
     * @param name File name.
     */
    ExampleCode(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
