/*
 * Property of Opencore
 */

package com.anilang.context;

import com.anilang.context.impl.IdentifierType;
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
}
