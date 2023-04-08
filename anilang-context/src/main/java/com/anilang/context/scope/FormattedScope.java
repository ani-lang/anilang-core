/*
 * Property of ani-lang project.
 */

package com.anilang.context.scope;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an scope already defined.
 *
 * @since 0.7.1
 */
public final class FormattedScope implements Scope {

    /**
     * Formatted scope.
     */
    private final String scope;

    /**
     * Ctor.
     *
     * @param parents List of parents.
     */
    public FormattedScope(final List<String> parents) {
        this(parents.stream().collect(Collectors.joining("$", "$", "")));
    }

    /**
     * Ctor.
     *
     * @param scope Formatted scope.
     */
    public FormattedScope(final String scope) {
        this.scope = scope;
    }

    @Override
    public String formatted() {
        return this.scope;
    }
}
