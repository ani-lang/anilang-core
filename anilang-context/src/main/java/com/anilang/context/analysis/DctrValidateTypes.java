/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import java.util.function.Consumer;

/**
 * Run {@link TypeValidation}.
 *
 * @since 0.7.1
 */
final class DctrValidateTypes implements FileAnalysis {

    /**
     * Decorated.
     */
    private final FileAnalysis origin;

    /**
     * Accept error if any.
     */
    private final Consumer<String> consumer;

    /**
     * Ctor.
     *
     * @param origin Decorated.
     * @param consumer Accept error if any.
     */
    DctrValidateTypes(final FileAnalysis origin, final Consumer<String> consumer) {
        this.origin = origin;
        this.consumer = consumer;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        new TypeValidation(args.context())
            .error()
            .ifPresent(this.consumer);
        return args;
    }
}
