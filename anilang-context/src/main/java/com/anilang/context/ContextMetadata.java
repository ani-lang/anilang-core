/*
 * Property of Opencore
 */

package com.anilang.context;

import com.anilang.context.impl.IdentifierType;
import java.util.Optional;
import org.antlr.v4.runtime.Token;

/**
 * Metadata for context.
 *
 * @since 0.7.0
 */
public interface ContextMetadata {

    /**
     * Context is a string, so we can compare and use substring to know if two members belong to
     * the same context.
     * TODO replace scope parent string with an object to avoid confusion
     * its confusing to have more than one name: parents, parentKay, scope, etc.
     * lets use 1 and represented on an object.
     *
     * @return Formatted parents.
     */
    String getParents();

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
     * @return Key or empty if type is {@link Type#UNKNOWN}.
     */
    Optional<String> getTypeReferenceKey();

    /**
     * Update the reference key.
     *
     * @param referenceKey Key.
     */
    void setTypeReferenceKey(String referenceKey);
}
