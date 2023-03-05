/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import java.util.Optional;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

/**
 * Within a context, it looks for an identifier.
 *
 * @since 0.7.0
 */
public final class LookupParentContext {

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
    public LookupParentContext(
        final AniContext context,
        final String identifier,
        final ParserRuleContext rule
    ) {
        this.context = context;
        this.identifier = identifier;
        this.rule = rule;
    }

    /**
     * Add the rule as {@link IdentifierType#REFERENCE} to the context if it has been declared.
     *
     * @since 0.7.0
     */
    public void addIfFound() {
        this.getKey().ifPresent(
            ctxKey -> this.context.addContext(
                new BaseEntry(
                    this.rule,
                    this.identifier,
                    this.context.getDeclarationKey(ctxKey),
                    IdentifierType.REFERENCE
                )
            )
        );
    }

    /**
     * Return the top-down parent string.
     *
     * @return Parents.
     * @checkstyle ReturnCountCheck (25 lines)
     */
    @SuppressWarnings("PMD.OnlyOneReturn")
    public Optional<String> getKey() {
        final String scope = new ReversedCtxPath(
            new CtxPathList(this.rule, this.identifier).asList()
        ).toString();
        final String[] parents = scope.split("\\$");
        final String start = String.format("$%s", this.identifier);
        String prefix = "";
        // @checkstyle MethodBodyCommentsCheck (2 lines)
        // start from 1 to avoid the first empty item in "$file$a$b..."
        // stops at (parents.length - 1) to avoid the last value which is the identifier
        // @checkstyle IllegalTokenCheck (1 line)
        for (int index = 1; index < parents.length - 1; index++) {
            final String parent = parents[index];
            prefix = String.format("%s$%s", prefix, parent);
            final String key = String.format("%s%s", prefix, start);
            if (
                this.context.hasDeclaration(key)
                    && this.isParentBefore(key, this.rule.getStart())
            ) {
                return Optional.of(key);
            }
        }
        return Optional.empty();
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
