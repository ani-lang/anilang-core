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
        // TODO: 31-01-23 test case: only declaration, no reference
        return aniContext.asMap()
            .entrySet()
            .stream()
            .filter(item -> item.getValue().getIdentifierType() == IdentifierType.DECLARATION)
            .collect(
                Collectors.toMap(
                    entry -> entry.getValue().getParents(),
                    Map.Entry::getKey
                )
            );
    }
}
