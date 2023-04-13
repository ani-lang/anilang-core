/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.scope.Scope;
import java.util.Optional;
import org.antlr.v4.runtime.Token;

/**
 * Metadata related to an identifier.
 *
 * @since 0.7.0
 */
public final class BaseCtxMetadata implements ContextMetadata {

    /**
     * Scope.
     */
    private final Scope scope;

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
     * The key to get information about the type resolved.
     */
    private String reference;

    /**
     * Identifier name.
     */
    private final String name;

    /**
     * Ctor.
     *
     * @param scope Scope.
     * @param declaration Key to find the referenced declaration.
     * @param start Represent the start of the identifier.
     * @param identifierType Identifier type.
     * @param type Resolved type.
     * @param reference Key to the type definition.
     * @param name Identifier name.
     * @checkstyle ParameterNumberCheck (20 lines)
     * @checkstyle ParameterNameCheck (20 lines)
     */
    public BaseCtxMetadata(
        final Scope scope,
        final String declaration,
        final Token start,
        final IdentifierType identifierType,
        final Type type,
        final String reference,
        final String name
    ) {
        this.scope = scope;
        this.declaration = declaration;
        this.start = start;
        this.identifierType = identifierType;
        this.type = type;
        this.reference = reference;
        this.name = name;
    }

    @Override
    public Scope getScope() {
        return this.scope;
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

    @Override
    public Optional<String> getReference() {
        return Optional.of(this.reference)
            .filter(item -> !item.isEmpty());
    }

    @Override
    public void setReference(final String key) {
        this.reference = key;
    }

    @Override
    public String name() {
        return this.name;
    }
}
