/*
 * Property of Opencore
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
    private final ParserRuleContext ctx;

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
     * @param ctx Context.
     * @param identifier Identifier.
     */
    public BaseEntry(
        final ParserRuleContext ctx,
        final String identifier
    ) {
        this(ctx, identifier, new PositionKey(ctx).toString(), IdentifierType.DECLARATION);
    }

    /**
     * Ctor.
     * TODO  More than 3 parameters (found 4)
     *
     * @param ctx Context.
     * @param identifier Identifier.
     * @param declaration Key to find the referenced declaration.
     * @param type Identifier type.
     * @checkstyle ParameterNumberCheck (20 lines)
     */
    public BaseEntry(
        final ParserRuleContext ctx,
        final String identifier,
        final String declaration,
        final IdentifierType type
    ) {
        this.ctx = ctx;
        this.identifier = identifier;
        this.declaration = declaration;
        this.type = type;
    }

    @Override
    public String getKey() {
        return new PositionKey(this.ctx).toString();
    }

    @Override
    public ContextMetadata getValue() {
        return new BaseCtxMetadata(
            new FormattedScope(
                new ListParents(
                    this.ctx,
                    this.identifier
                ).asList()
            ).formatted(),
            this.declaration,
            this.ctx.getStart(),
            this.type,
            Type.UNKNOWN,
            ""
        );
    }
}
