/*
 * Property of ani-lang project.
 */

package com.anilang.context.utils;

import com.anilang.context.scope.Scope;

/**
 * Compares the given scope to the default scope.
 *
 * @since 0.7.1
 */
public final class IsDefaultScope {

    /**
     * Scope to compare.
     */
    private final Scope scope;

    /**
     * Ctor.
     *
     * @param scope Scope to compare.
     */
    public IsDefaultScope(final Scope scope) {
        this.scope = scope;
    }

    /**
     * True or false.
     *
     * @return Is default scope.
     */
    public boolean value() {
        return Scope.DEFAULT_SCOPE.equals(this.scope.formatted());
    }

    /**
     * Negation of value.
     *
     * @return Is not default scope.
     */
    public boolean not() {
        return !this.value();
    }
}
