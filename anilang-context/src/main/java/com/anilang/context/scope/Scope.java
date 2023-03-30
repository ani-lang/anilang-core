/*
 * Property of Opencore
 */

package com.anilang.context.scope;

/**
 * Represents a scope for a identifier.
 *
 * @since 0.7.1
 */
public interface Scope {

    /**
     * Formats the scope by separating each layer using the symbol '$'.
     *
     * @return Formatted scope.
     */
    String formatted();
}
