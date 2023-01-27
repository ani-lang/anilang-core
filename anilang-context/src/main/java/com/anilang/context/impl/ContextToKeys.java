/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import java.util.Map;
import java.util.stream.Collectors;

public final class ContextToKeys {
    private final AniContext aniContext;

    public ContextToKeys(final AniContext aniContext) {
        this.aniContext = aniContext;
    }

    public Map<String, String> asMap() {
        return aniContext.asMap()
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    entry -> entry.getValue().getParents(),
                    Map.Entry::getKey
                )
            );
    }
}
