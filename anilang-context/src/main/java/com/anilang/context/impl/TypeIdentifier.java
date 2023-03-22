/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.parser.antlr.AniParser;
import java.util.Optional;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Resolve the type identifier from the type rule {@link AniParser.TypeContext}
 *
 * @since 0.7.0
 */
public final class TypeIdentifier {

    /**
     * Type rule.
     */
    private final AniParser.TypeContext type;

    /**
     * Ctor.
     *
     * @param type Rule.
     */
    public TypeIdentifier(final AniParser.TypeContext type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return Optional.ofNullable(this.type.Identifier())
            .map(ParseTree::getText)
            .orElseGet(() -> this.type.primitiveType().getText());
    }
}
