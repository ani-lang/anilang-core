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
 */
public final class ReversedCtxPath implements CtxPath {

    private final List<String> ctxPath;

    public ReversedCtxPath(final List<String> ctxPath) {
        this.ctxPath = ctxPath;
    }

    @Override
    public List<String> asList() {
        List<String> sorted = new LinkedList<>();
        Collections.copy(sorted, ctxPath);
        Collections.reverse(sorted);
        return sorted;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = ctxPath.size() - 1; i >= 0; i--) {
            stringBuilder.append("$");
            stringBuilder.append(ctxPath.get(i));
        }
        return stringBuilder.toString();
    }
}
