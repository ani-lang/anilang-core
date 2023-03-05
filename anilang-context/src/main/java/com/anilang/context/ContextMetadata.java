/*
 * Property of Opencore
 */

package com.anilang.context;

import com.anilang.context.impl.IdentifierType;
import org.antlr.v4.runtime.Token;

public interface ContextMetadata {

    /**
     * Context is a string, so we can compare and use substring to know if two members belong to
     * the same context.
     *
     * @return formatted parents.
     */
    String getParents();

    /**
     * Key to the declaration context.
     *
     * @return context key.
     */
    String getDeclarationKey();

    /**
     * Represents the start token.
     *
     * @return token from start.
     */
    Token getStart();

    /**
     * See types {@link IdentifierType}.
     *
     * @return identifier type.
     */
    IdentifierType getIdentifierType();

    Type getType();

    /**
     * Update the type.
     * @param type
     */
    void asType(Type type);
}
