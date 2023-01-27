/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public final class LookupExistingContext {
    private final Map<String, String> contextToKeys;
    private final AniContext aniContext;
    private final String identifier;
    private final ParserRuleContext ctx;

    public LookupExistingContext(final Map<String, String> contextToKeys,
                                 final AniContext aniContext,
                                 final String identifier,
                                 final ParserRuleContext ctx) {
        this.contextToKeys = contextToKeys;
        this.aniContext = aniContext;
        this.identifier = identifier;
        this.ctx = ctx;
    }

    public void addIfFound() {
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
            final String key = prefix + identifierPrefix;
            if (contextToKeys.containsKey(key) && isParentBefore(key, ctx.getStart())) {
                aniContext.addContext(
                    new BaseEntry(
                        ctx,
                        identifier,
                        contextToKeys.get(key)
                    )
                );
            }
        }
    }

    private boolean isParentBefore(final String ctxKey, final Token token) {
        return aniContext.get(contextToKeys.get(ctxKey)).getStart().getLine() < token.getLine();
    }
}
