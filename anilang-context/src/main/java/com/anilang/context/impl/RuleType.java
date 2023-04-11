/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.parser.antlr.AniParser;
import java.util.Optional;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Resolve the type identifier from the type rule {@link AniParser.TypeContext}.
 *
 * @since 0.7.0
 */
public final class RuleType {

    /**
     * Type rule.
     */
    private final AniParser.TypeContext type;

    /**
     * Ctor.
     *
     * @param type Rule.
     */
    public RuleType(final AniParser.TypeContext type) {
        this.type = type;
    }

    /**
     * Return a String of the type.
     *
     * @return String.
     */
    public String raw() {
        return Optional.ofNullable(this.type.Identifier())
            .map(ParseTree::getText)
            .orElseGet(() -> this.type.primitiveType().getText());
    }
}
