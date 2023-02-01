/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.ContextEntry;
import com.anilang.context.ContextMetadata;
import org.antlr.v4.runtime.ParserRuleContext;

public class BaseEntry implements ContextEntry {
    protected final ParserRuleContext ctx;
    private final String identifier;
    private final String declarationKey;
    private final IdentifierType identifierType;

    public BaseEntry(final ParserRuleContext ctx,
                     final String identifier) {
        this(ctx, identifier, new PositionKey(ctx).toString(), IdentifierType.DECLARATION);
    }

    public BaseEntry(final ParserRuleContext ctx,
                     final String identifier,
                     final String declarationKey,
                     final IdentifierType identifierType) {
        this.ctx = ctx;
        this.identifier = identifier;
        this.declarationKey = declarationKey;
        this.identifierType = identifierType;
    }

    @Override
    public String getKey() {
        return new PositionKey(ctx).toString();
    }

    @Override
    public ContextMetadata getValue() {
        return new BaseCtxMetadata(
            new ReversedCtxPath(
                new CtxPathList(ctx, identifier).asList()
            ).toString(),
            declarationKey,
            ctx.getStart(),
            identifierType
        );
    }
}
