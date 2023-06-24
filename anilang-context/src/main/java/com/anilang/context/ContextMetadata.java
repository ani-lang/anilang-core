/*
 * Property of ani-lang project.
 */

package com.anilang.context;

import com.anilang.context.impl.IdentifierType;
import com.anilang.context.scope.Scope;
import java.util.Optional;
import org.antlr.v4.runtime.Token;

/**
 * Metadata for context.
 *
 * @since 0.7.0
 */
public interface ContextMetadata {

    /**
     * Scope is a string, so we can compare and use substring to know if two members belong to
     * the same scope.
     *
     * @return Formatted parents.
     */
    Scope getScope();

    /**
     * Key to the declaration context.
     *
     * @return Context key.
     */
    String getDeclaration();

    /**
     * Represents the start token.
     *
     * @return Token from start.
     */
    Token getStart();

    /**
     * See types {@link IdentifierType}.
     *
     * @return Identifier type.
     */
    IdentifierType getIdentifierType();

    /**
     * The resolved type.
     *
     * @return Type.
     */
    Type getType();

    /**
     * Update the type.
     *
     * @param type Type.
     */
    void asType(Type type);

    /**
     * Returns the key to get information about the type resolved.
     *
     * @return Key or empty if type is {@link Type#UNKNOWN} or a primitive type.
     */
    Optional<String> getReference();

    /**
     * Update the reference key.
     *
     * @param reference Key.
     */
    void setReference(String reference);

    /**
     * Returns the file where this was imported.
     * The reference key can be used in this file.
     * @return File name where it was imported from.
     */
    Optional<String> getFileSource();

    void setFileSource(String source);

    /**
     * Identifier name.
     *
     * @return Name.
     */
    String name();

    void setIdentifierType(IdentifierType identifierType);
}
