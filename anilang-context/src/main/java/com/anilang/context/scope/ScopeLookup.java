/*
 * Property of ani-lang project.
 */

package com.anilang.context.scope;

import com.anilang.context.AniContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

/**
 * Looks up for the identifier's scope within the context.
 *
 * @since 0.7.0
 */
public final class ScopeLookup {

    /**
     * Full ani context.
     */
    private final AniContext context;

    /**
     * Identifier.
     */
    private final String identifier;

    /**
     * Rule.
     */
    private final ParserRuleContext rule;

    /**
     * Ctor.
     *
     * @param context Full ani context.
     * @param identifier Identifier.
     * @param rule Rule.
     */
    public ScopeLookup(
        final AniContext context,
        final String identifier,
        final ParserRuleContext rule
    ) {
        this.context = context;
        this.identifier = identifier;
        this.rule = rule;
    }

    /**
     * Looks for the scope and return it if found.
     *
     * @return Scope if found.
     */
    public Scope scope() {
        final String formatted = new FormattedScope(
            new ListParents(
                this.rule,
                this.identifier
            ).asList()
        ).formatted();
        final String[] parents = formatted.split("\\$");
        final String start = String.format("$%s", this.identifier);
        String prefix = "";
        // @checkstyle MethodBodyCommentsCheck (2 lines)
        // start from 1 to avoid the first empty item in "$file$a$b..."
        // stops at (parents.length - 1) to avoid the last value which is the identifier
        // @checkstyle IllegalTokenCheck (1 line)
        for (int index = 1; index < parents.length - 1; index++) {
            final String parent = parents[index];
            prefix = String.format("%s$%s", prefix, parent);
            final Scope candidate = new FormattedScope(
                String.format("%s%s", prefix, start)
            );
            if (
                this.context.hasDeclaration(candidate.formatted())
                    && this.isParentBefore(candidate.formatted(), this.rule.getStart())
            ) {
                return candidate;
            }
        }
        return new DefaultScope();
    }

    /**
     * Declarations must be before references.
     *
     * @param key Rule declaration key.
     * @param token Reference.
     * @return True if declaration is before reference.
     */
    private boolean isParentBefore(final String key, final Token token) {
        return this.context.get(
            this.context.getDeclarationKey(key)
        ).getStart().getLine() < token.getLine();
    }
}
