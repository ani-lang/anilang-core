/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.context.ContextEntry;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.scope.FormattedScope;
import com.anilang.context.scope.ListParents;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Basic implementation of an entry containing information about an identifier and its key.
 *
 * @since 0.7.0
 */
public final class BaseEntry implements ContextEntry {

    /**
     * The rule context.
     */
    private final ParserRuleContext rule;

    /**
     * Identifier name.
     */
    private final String identifier;

    /**
     * Key to find the referenced declaration.
     */
    private final String declaration;

    /**
     * Identifier type.
     */
    private final IdentifierType type;

    /**
     * Ctor.
     *
     * @param rule Rule.
     * @param identifier Identifier.
     */
    public BaseEntry(
        final ParserRuleContext rule,
        final String identifier
    ) {
        this(rule, identifier, new PositionKey(rule).toString(), IdentifierType.DECLARATION);
    }

    /**
     * Ctor.
     * TODO  More than 3 parameters (found 4)
     *
     * @param rule Rule.
     * @param identifier Identifier.
     * @param declaration Key to find the referenced declaration.
     * @param type Identifier type.
     * @checkstyle ParameterNumberCheck (20 lines)
     */
    public BaseEntry(
        final ParserRuleContext rule,
        final String identifier,
        final String declaration,
        final IdentifierType type
    ) {
        this.rule = rule;
        this.identifier = identifier;
        this.declaration = declaration;
        this.type = type;
    }

    @Override
    public String getKey() {
        return new PositionKey(this.rule).toString();
    }

    @Override
    public ContextMetadata getValue() {
        return new BaseCtxMetadata(
            new FormattedScope(
                new ListParents(
                    this.rule,
                    this.identifier
                ).asList()
            ),
            this.declaration,
            this.rule.getStart(),
            this.type,
            Type.UNKNOWN,
            "",
            this.identifier
        );
    }
}
