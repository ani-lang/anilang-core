/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.ContextEntry;
import com.anilang.context.ContextMetadata;
import com.anilang.context.scope.ScopeLookup;
import com.anilang.context.utils.IsDefaultScope;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
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
    private final String key;
    private List<Path> imports;

    public BaseAniContext() {
        this("");
    }

    public BaseAniContext(final String key) {
        this.key = key;
    }

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public void addContext(final ContextEntry entry) {
        if (this.metadata.containsKey(entry.getKey())) {
            System.err.printf("duplicated entry %s%n", entry.getKey());
        } else {
            if (entry.getValue().getIdentifierType() == IdentifierType.DECLARATION) {
                this.keys.put(entry.getValue().getScope().formatted(), entry.getKey());
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

    @Override
    public void setImports(final List<Path> imports) {
        this.imports = imports;
    }

    @Override
    public List<Path> getImports() {
        return this.imports;
    }

    @Override
    public String contextKey() {
        return this.key;
    }
}
