/*
 * Property of Opencore
 */

package com.anilang.context.analysis;

import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * Run {@link SourceValidation}.
 *
 * @since 0.7.1
 */
final class DctrValidateSource implements FileAnalysis {

    /**
     * Decorated.
     */
    private final FileAnalysis origin;

    /**
     * Accept error if any.
     */
    private final Consumer<String> consumer;

    /**
     * Path to analyze.
     */
    private final Path path;

    /**
     * Ctor.
     *
     * @param origin Decorated.
     * @param path Path to analyze.
     * @param consumer Accept error if any.
     */
    DctrValidateSource(
        final FileAnalysis origin,
        final Path path,
        final Consumer<String> consumer
    ) {
        this.origin = origin;
        this.path = path;
        this.consumer = consumer;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        new SourceValidation(this.path)
            .error()
            .ifPresent(this.consumer);
        return args;
    }
}
