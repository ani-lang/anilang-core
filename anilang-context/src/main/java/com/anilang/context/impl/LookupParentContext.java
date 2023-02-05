/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import java.util.Optional;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public final class LookupParentContext {
    private final AniContext aniContext;
    private final String identifier;
    private final ParserRuleContext ctx;

    public LookupParentContext(final AniContext aniContext,
                               final String identifier,
                               final ParserRuleContext ctx) {
        this.aniContext = aniContext;
        this.identifier = identifier;
        this.ctx = ctx;
    }

    public void addIfFound() {
        getKey().ifPresent(
            ctxKey -> aniContext.addContext(
                new BaseEntry(
                    ctx,
                    identifier,
                    aniContext.getDeclarationKey(ctxKey),
                    IdentifierType.REFERENCE
                )
            )
        );
    }

    public Optional<String> getKey() {
        final String scopePath = new ReversedCtxPath(
            new CtxPathList(ctx, identifier).asList()
        ).toString();
        String[] parents = scopePath.split("\\$");
        final String identifierPrefix = "$" + identifier;
        String prefix = "";
        // start from 1 to avoid the first empty item in "$file$a$b..."
        // stops at (parents.length - 1) to avoid the last value which is the identifier
        for (int i = 1; i < parents.length - 1; i++) {
            final String parent = parents[i];
            prefix = prefix + "$" + parent;
            final String ctxKey = prefix + identifierPrefix;
            if (aniContext.hasDeclaration(ctxKey) && isParentBefore(ctxKey, ctx.getStart())) {
                return Optional.of(ctxKey);
            }
        }
        return Optional.empty();
    }

    /**
     * Declarations must be before references.
     *
     * @param ctxKey ctx declaration key.
     * @param token reference.
     * @return true if declaration is before reference.
     */
    private boolean isParentBefore(final String ctxKey, final Token token) {
        return aniContext.get(aniContext.getDeclarationKey(ctxKey)).getStart().getLine() < token.getLine();
    }
}
