/*
 * Property of Opencore
 */

package com.anilang.context;

import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;

public interface AniContext {
    void addContext(ContextEntry contextEntry);

    boolean contains(String key);

    ContextMetadata get(String key);

    int size();

    Map<String, ContextMetadata> asMap();

    boolean hasDeclaration(ParserRuleContext ctx, String text);

    boolean hasDeclaration(String ctxKey);

    String getDeclarationKey(String ctxKey);
}
