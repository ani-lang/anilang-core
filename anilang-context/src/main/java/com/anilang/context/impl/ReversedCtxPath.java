/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.CtxPath;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Sort the context path in reverse order.
 *
 * @since 0.7.0
 */
public final class ReversedCtxPath implements CtxPath {

    /**
     * Bottom-up context path.
     */
    private final List<String> ctx;

    /**
     * Ctor.
     *
     * @param ctx Bottom-up context path.
     */
    public ReversedCtxPath(final List<String> ctx) {
        this.ctx = ctx;
    }

    @Override
    public List<String> asList() {
        final List<String> sorted = new LinkedList<>();
        Collections.copy(sorted, this.ctx);
        Collections.reverse(sorted);
        return sorted;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        // @checkstyle IllegalTokenCheck (1 line)
        for (int index = this.ctx.size() - 1; index >= 0; index--) {
            builder.append(String.format("$%s", this.ctx.get(index)));
        }
        return builder.toString();
    }
}
