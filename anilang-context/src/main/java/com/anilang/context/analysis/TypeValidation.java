/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import java.util.Optional;

/**
 * Validate types in a context.
 *
 * @since 0.7.1
 */
final class TypeValidation {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param context Context.
     */
    TypeValidation(final AniContext context) {
        this.context = context;
    }

    /**
     * Error.
     *
     * @return Error if any.
     */
    Optional<String> error() {
        return this.context.asMap()
            .keySet()
            .stream()
            .filter(key -> this.context.get(key).getType() == Type.UNKNOWN)
            .limit(1)
            .findFirst()
            .map(
                key -> {
                    final ContextMetadata metadata = this.context.get(key);
                    return String.format(
                        "Cannot resolve type of var '%s' at [%s,%s]",
                        metadata.name(),
                        metadata.getStart().getLine(),
                        metadata.getStart().getCharPositionInLine() + 1
                    );
                }
            );
    }
}
