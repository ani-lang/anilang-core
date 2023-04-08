/*
 * Property of Opencore
 */

package com.anilang.context.analysis;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Validates the source file.
 *
 * @since 0.7.1
 */
final class SourceValidation {

    /**
     * File to validate.
     */
    private final Path path;

    /**
     * Ctor.
     *
     * @param path File to validate.
     */
    SourceValidation(final Path path) {
        this.path = path;
    }

    /**
     * @return Error if any.
     */
    Optional<String> error() {
        if (!this.path.endsWith(".ani")) {
            return Optional.of(
                String.format(
                    "File extension not recognized: %s",
                    this.path.getFileName()
                )
            );
        }
        return Optional.empty();
    }
}
