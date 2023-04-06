/*
 * Property of Opencore
 */

package com.anilang.context.utils;

import com.anilang.context.AniContext;
import com.anilang.context.scope.Scope;

/**
 * The key of the scope within a context.
 *
 * @since 0.7.1
 */
public final class ScopeKey {

    /**
     * Scope.
     */
    private final String scope;

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param scope Scope.
     * @param context Context.
     */
    public ScopeKey(final String scope, final AniContext context) {
        this.scope = scope;
        this.context = context;
    }

    /**
     * Returns the key for a scope.
     *
     * @return The key of the scope. {@link Scope#DEFAULT_SCOPE} if not found.
     */
    public String value() {
        if (this.context.hasDeclaration(this.scope)) {
            return this.context.getDeclarationKey(this.scope);
        }
        return Scope.DEFAULT_SCOPE;
    }
}
