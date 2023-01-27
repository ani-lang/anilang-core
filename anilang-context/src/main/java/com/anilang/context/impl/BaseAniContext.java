/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.ContextEntry;
import com.anilang.context.ContextMetadata;
import java.util.HashMap;
import java.util.Map;

public final class BaseAniContext implements AniContext {


    // TODO: 22-01-23 string too slow, must be a way to use numbers
    /**
     * Token identifier (position) and its metadata including its context string.
     */
    private final Map<String, ContextMetadata> map = new HashMap<>();

    @Override
    public void addContext(final ContextEntry contextEntry) {
        // TODO: 25-01-23 what if it is already defined? should throw an error
        if (map.containsKey(contextEntry.getKey())) {
            System.err.println("duplicated entry " + contextEntry.getKey());
        } else {
            map.put(contextEntry.getKey(), contextEntry.getValue());
        }
    }

    @Override
    public boolean contains(final String key) {
        return map.containsKey(key);
    }

    @Override
    public ContextMetadata get(final String key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Map<String, ContextMetadata> asMap() {
        return map;
    }
}
