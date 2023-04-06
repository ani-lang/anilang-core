/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.ContextEntry;
import com.anilang.context.ContextMetadata;
import com.anilang.context.scope.ScopeLookup;
import com.anilang.context.utils.IsDefaultScope;
import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Basic context for an Ani program.
 *
 * @since 0.7.0
 */
public final class BaseAniContext implements AniContext {

    /**
     * Token identifier (position) and its metadata including its context string.
     */
    private final Map<String, ContextMetadata> metadata = new HashMap<>();

    /**
     * Scope declaration to its key in metadata.
     */
    private final Map<String, String> keys = new HashMap<>();

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public void addContext(final ContextEntry entry) {
        // @checkstyle MethodBodyCommentsCheck (10 lines)
        // TODO 25-01-23 what if it is already defined? should throw an error
        if (this.metadata.containsKey(entry.getKey())) {
            System.err.printf("duplicated entry %s%n", entry.getKey());
        } else {
            if (entry.getValue().getIdentifierType() == IdentifierType.DECLARATION) {
                this.keys.put(entry.getValue().getParents(), entry.getKey());
            }
            this.metadata.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean contains(final String key) {
        return this.metadata.containsKey(key);
    }

    @Override
    public ContextMetadata get(final String key) {
        return this.metadata.get(key);
    }

    @Override
    public int size() {
        return this.metadata.size();
    }

    @Override
    public Map<String, ContextMetadata> asMap() {
        return this.metadata;
    }

    @Override
    public boolean hasDeclaration(final ParserRuleContext rule, final String identifier) {
        return new IsDefaultScope(
            new ScopeLookup(
                this,
                identifier,
                rule
            ).scope()
        ).not();
    }

    @Override
    public boolean hasDeclaration(final String parent) {
        return this.keys.containsKey(parent);
    }

    @Override
    public String getDeclarationKey(final String parent) {
        return this.keys.get(parent);
    }
}
