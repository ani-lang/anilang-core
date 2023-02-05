/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.CtxPath;
import com.anilang.parser.antlr.AniParser;
import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Save only non-empty parents.
 */
public final class CtxPathList implements CtxPath {
    private final ParserRuleContext ctx;
    private final String identifier;

    public CtxPathList(final ParserRuleContext ctx, final String identifier) {
        this.ctx = ctx;
        this.identifier = identifier;
    }

    @Override
    public List<String> asList() {
        final List<String> parents = new LinkedList<>();
        parents.add(identifier);
        ParserRuleContext parserRuleContext = ctx;
        do {
            parserRuleContext = parserRuleContext.getParent();
            final String formattedParent = new FormattedScopedParent(parserRuleContext).toString();
            if (!formattedParent.isEmpty()) {
                parents.add(formattedParent);
            }
        } while (!(parserRuleContext instanceof AniParser.FileContext));
        return parents;
    }
}
