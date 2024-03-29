/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.scope.Scope;
import com.anilang.context.scope.ScopeLookup;
import com.anilang.context.scope.IsDefaultScope;
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
     * TODO this method name looks weird, rename it or refactor it to make it more readable
     * #144
     *
     * @since 0.7.1
     */
    public void ifFound() {
        final Scope scope = new ScopeLookup(
            this.context,
            this.identifier,
            this.rule
        ).scope();
        if (new IsDefaultScope(scope).not()) {
            this.context.addContext(
                new BaseEntry(
                    this.rule,
                    this.identifier,
                    this.context.getDeclarationKey(scope.formatted()),
                    IdentifierType.REFERENCE
                )
            );
        }
    }

    public void ifNotFound() {
        final Scope scope = new ScopeLookup(
            this.context,
            this.identifier,
            this.rule
        ).scope();
        if (new IsDefaultScope(scope).value()) {
            this.context.addContext(
                new BaseEntry(
                    this.rule,
                    this.identifier,
                    "",
                    IdentifierType.REFERENCE
                )
            );
        }
    }
}
