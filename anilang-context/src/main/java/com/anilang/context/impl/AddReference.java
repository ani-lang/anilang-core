/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.scope.ScopeLookup;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Add the identifier as reference if found in the rule's context.
 *
 * @since 0.7.1
 */
public final class AddReference {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Identifier to lookup.
     */
    private final String identifier;

    /**
     * Starter rule.
     */
    private final ParserRuleContext rule;

    /**
     * Ctor.
     *
     * @param context Full ani context.
     * @param identifier Identifier.
     * @param rule Rule.
     */
    public AddReference(
        final AniContext context,
        final String identifier,
        final ParserRuleContext rule
    ) {
        this.context = context;
        this.identifier = identifier;
        this.rule = rule;
    }

    /**
     * Add the rule as {@link IdentifierType#REFERENCE} to the context if it has been declared.
     *
     * @since 0.7.1
     */
    public void ifFound() {
        new ScopeLookup(
            this.context,
            this.identifier,
            this.rule
        ).scope().ifPresent(
            scopeString -> this.context.addContext(
                new BaseEntry(
                    this.rule,
                    this.identifier,
                    this.context.getDeclarationKey(scopeString.formatted()),
                    IdentifierType.REFERENCE
                )
            )
        );
    }
}
