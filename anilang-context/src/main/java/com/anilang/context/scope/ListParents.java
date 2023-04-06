/*
 * Property of Opencore
 */

/*
 * Property of Opencore
 */

package com.anilang.context.scope;

import com.anilang.parser.antlr.AniParser;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Save only non-empty parents.
 *
 * @since 0.7.1
 */
public final class ListParents {

    /**
     * Rule.
     */
    private final ParserRuleContext rule;

    /**
     * Identifier.
     */
    private final String identifier;

    /**
     * Ctor.
     *
     * @param rule Rule.
     * @param identifier Identifier.
     */
    public ListParents(final ParserRuleContext rule, final String identifier) {
        this.rule = rule;
        this.identifier = identifier;
    }

    /**
     * List of parents sorted from left to right.
     *
     * @return Parents.
     */
    public List<String> asList() {
        final LinkedList<String> parents = new LinkedList<>();
        parents.add(this.identifier);
        ParserRuleContext curent = this.rule;
        do {
            curent = curent.getParent();
            final String parent = new FormattedScopedParent(curent).toString();
            if (!parent.isEmpty()) {
                parents.add(parent);
            }
        } while (!(curent instanceof AniParser.FileContext));
        Collections.reverse(parents);
        return parents;
    }
}
