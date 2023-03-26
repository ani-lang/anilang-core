/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.PositionKey;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This phase saved each type definition.
 *
 * @since 0.7.0
 */
public final class TypeDefinitionListener extends AniBaseListener {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param context Context.
     */
    public TypeDefinitionListener(final AniContext context) {
        this.context = context;
    }

    @Override
    public void enterClassDeclaration(final AniParser.ClassDeclarationContext rule) {
        this.asType(rule, Type.CLASS);
    }

    @Override
    public void enterStructDeclaration(final AniParser.StructDeclarationContext rule) {
        this.asType(rule, Type.STRUCT);
    }

    /**
     * Resolve a type.
     * In the definition phase, the reference type key the rule's key since we are defining types.
     *
     * @param rule Rule.
     * @param type Type.
     */
    private void asType(final ParserRuleContext rule, final Type type) {
        final String key = new PositionKey(rule).toString();
        if (this.context.contains(key)) {
            final ContextMetadata metadata = this.context.get(key);
            metadata.asType(type);
            metadata.setTypeReferenceKey(key);
        }
    }
}
