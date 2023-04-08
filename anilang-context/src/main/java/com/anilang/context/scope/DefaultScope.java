/*
 * Property of ani-lang project.
 */

package com.anilang.context.scope;

/**
 * Represents a default scope. Used when there is no valid scope for an identifier within a context.
 *
 * @since 0.7.1
 */
public final class DefaultScope implements Scope {

    @Override
    public String formatted() {
        return Scope.DEFAULT_SCOPE;
    }
}
