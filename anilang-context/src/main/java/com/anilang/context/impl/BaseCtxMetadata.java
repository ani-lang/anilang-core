/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import org.antlr.v4.runtime.Token;

/**
 * Metadata related to an identifier.
 *
 * @since 0.7.0
 */
public final class BaseCtxMetadata implements ContextMetadata {

    /**
     * Parent scope.
     */
    private final String parents;

    /**
     * Key to find the referenced declaration.
     */
    private final String declaration;

    /**
     * Represent the start of the identifier.
     */
    private final Token start;

    /**
     * Identifier type.
     * TODO identifierType name ambiguity with type
     * Name 'identifierType' must match pattern '^(id|[a-z]{3,12})$'
     *
     * @checkstyle MemberNameCheck (5 lines)
     */
    private final IdentifierType identifierType;

    /**
     * Resolved type.
     */
    private Type type;

    /**
     * Ctor.
     *
     * @param parents Parent scope.
     * @param declaration Key to find the referenced declaration.
     * @param start Represent the start of the identifier.
     * @param identifierType Identifier type.
     * @param type Resolved type.
     * @checkstyle ParameterNumberCheck (20 lines)
     * @checkstyle ParameterNameCheck (20 lines)
     */
    public BaseCtxMetadata(
        final String parents,
        final String declaration,
        final Token start,
        final IdentifierType identifierType,
        final Type type
    ) {
        this.parents = parents;
        this.declaration = declaration;
        this.start = start;
        this.identifierType = identifierType;
        this.type = type;
    }

    @Override
    public String getParents() {
        return this.parents;
    }

    @Override
    public String getDeclaration() {
        return this.declaration;
    }

    @Override
    public Token getStart() {
        return this.start;
    }

    @Override
    public IdentifierType getIdentifierType() {
        return this.identifierType;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void asType(final Type value) {
        this.type = value;
    }
}
