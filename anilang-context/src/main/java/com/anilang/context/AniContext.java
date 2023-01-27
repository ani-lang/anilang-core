/*
 * Property of Opencore
 */

package com.anilang.context;

import java.util.Map;

public interface AniContext {
    void addContext(ContextEntry contextEntry);

    boolean contains(String key);

    ContextMetadata get(String key);

    int size();

    Map<String, ContextMetadata> asMap();
}
