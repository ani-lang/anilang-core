/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.ContextEntry;
import com.anilang.context.ContextMetadata;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;

public final class BaseAniContext implements AniContext {


    // TODO: 22-01-23 string too slow, must be a way to use numbers
    /**
     * Token identifier (position) and its metadata including its context string.
     */
    private final Map<String, ContextMetadata> map = new HashMap<>();

    /**
     * Context declaration to its key in map.
     */
    private final Map<String, String> contextToKeysMap = new HashMap<>();

    @Override
    public void addContext(final ContextEntry contextEntry) {
        // TODO: 25-01-23 what if it is already defined? should throw an error
        if (map.containsKey(contextEntry.getKey())) {
            System.err.println("duplicated entry " + contextEntry.getKey());
        } else {
            if (contextEntry.getValue().getIdentifierType() == IdentifierType.DECLARATION) {
                contextToKeysMap.put(contextEntry.getValue().getParents(), contextEntry.getKey());
            }
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

    @Override
    public boolean hasDeclaration(final ParserRuleContext ctx, final String identifier) {
        return new LookupParentContext(
            this,
            identifier,
            ctx
        ).getKey().isPresent();
    }

    @Override
    public boolean hasDeclaration(final String ctxKey) {
        return contextToKeysMap.containsKey(ctxKey);
    }

    @Override
    public String getDeclarationKey(final String ctxKey) {
        return contextToKeysMap.get(ctxKey);
    }
}
