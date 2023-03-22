/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.ScopePath;
import com.anilang.parser.antlr.AniParser;
import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Save only non-empty parents.
 *
 * @since 0.7.0
 */
public final class ScopePathList implements ScopePath {

    /**
     * Rule.
     */
    private final ParserRuleContext rule;

    /**
     * Identifier.
     */
    private final String identifier;

    /**
     * Ctor.
     *
     * @param rule Rule.
     * @param identifier Identifier.
     */
    public ScopePathList(final ParserRuleContext rule, final String identifier) {
        this.rule = rule;
        this.identifier = identifier;
    }

    @Override
    public List<String> asList() {
        final List<String> parents = new LinkedList<>();
        parents.add(this.identifier);
        ParserRuleContext curent = this.rule;
        do {
            curent = curent.getParent();
            final String parent = new FormattedScopedParent(curent).toString();
            if (!parent.isEmpty()) {
                parents.add(parent);
            }
        } while (!(curent instanceof AniParser.FileContext));
        return parents;
    }
}
