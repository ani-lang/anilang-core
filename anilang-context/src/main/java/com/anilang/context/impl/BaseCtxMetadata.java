/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.ContextMetadata;
import org.antlr.v4.runtime.Token;

public final class BaseCtxMetadata implements ContextMetadata {
    private final String parents;
    private final String declarationKey;
    private final Token start;
    private final IdentifierType identifierType;

    public BaseCtxMetadata(final String parents,
                           final String declarationKey,
                           final Token start,
                           final IdentifierType identifierType) {
        this.parents = parents;
        this.declarationKey = declarationKey;
        this.start = start;
        this.identifierType = identifierType;
    }

    @Override
    public String getParents() {
        return parents;
    }

    @Override
    public String getDeclarationKey() {
        return declarationKey;
    }

    @Override
    public Token getStart() {
        return start;
    }

    @Override
    public IdentifierType getIdentifierType() {
        return identifierType;
    }
}
