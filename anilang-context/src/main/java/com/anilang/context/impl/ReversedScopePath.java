/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.ScopePath;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Sort the scope path in reverse order.
 *
 * @since 0.7.0
 */
public final class ReversedScopePath implements ScopePath {

    /**
     * Bottom-up scope path.
     */
    private final List<String> scope;

    /**
     * Ctor.
     *
     * @param scope Bottom-up scope path.
     */
    public ReversedScopePath(final List<String> scope) {
        this.scope = scope;
    }

    @Override
    public List<String> asList() {
        final List<String> sorted = new LinkedList<>();
        Collections.copy(sorted, this.scope);
        Collections.reverse(sorted);
        return sorted;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        // @checkstyle IllegalTokenCheck (1 line)
        for (int index = this.scope.size() - 1; index >= 0; index--) {
            builder.append(String.format("$%s", this.scope.get(index)));
        }
        return builder.toString();
    }
}
