/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.CtxPathList;
import com.anilang.context.impl.LookupParentContext;
import com.anilang.context.impl.PositionKey;
import com.anilang.context.impl.ReversedCtxPath;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import java.util.Optional;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * TODO 28-02-23 resolve types used from class, struct and primitive types.
 * This phase resolve the types of identifiers using the defined types.
 *
 * @since 0.7.0
 */
public final class TypeResolveListener extends AniBaseListener {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param context Context.
     */
    public TypeResolveListener(final AniContext context) {
        this.context = context;
    }

    @Override
    public void enterStructBodyMember(final AniParser.StructBodyMemberContext ctx) {
        this.asType(
            ctx.type(),
            ctx.Identifier().getText(),
            new ReversedCtxPath(
                new CtxPathList(ctx, ctx.Identifier().getText()).asList()
            ).toString()
        );
    }

    @Override
    public void enterFormalParameterDecls(final AniParser.FormalParameterDeclsContext ctx) {
        final String identifier = Optional.ofNullable(ctx.type().Identifier())
            .map(ParseTree::getText)
            .orElseGet(() -> ctx.type().primitiveType().getText());
        this.asType(
            ctx.formalParameterDeclsRest().variableDeclaratorId(),
            identifier,
            new LookupParentContext(this.context, identifier, ctx).getKey().orElse("")
        );
    }

    /**
     * Resolve the type.
     * TODO 01-03-23 this is duplicated
     *
     * @param rule Rule.
     * @param identifier Identifier.
     * @param reference Reference key.
     */
    private void asType(
        final ParserRuleContext rule,
        final String identifier,
        final String reference
    ) {
        final String key = new PositionKey(rule).toString();
        if (this.context.contains(key)) {
            final ContextMetadata metadata = this.context.get(key);
            metadata.asType(this.getType(identifier, reference));
        }
    }

    /**
     * Resolve the type.
     *
     * @param identifier Identifier.
     * @param reference Key.
     * @return Type.
     * @checkstyle NPathComplexityCheck (35 lines)
     * @checkstyle ReturnCountCheck (35 lines)
     */
    @SuppressWarnings({"PMD.OnlyOneReturn", "PMD.NPathComplexity"})
    private Type getType(final String identifier, final String reference) {
        /* @checkstyle MethodBodyCommentsCheck (10 lines)
         * TODO this must come from the lexer. update the lexer definition
         */
        if (identifier.equals("boolean")) {
            return Type.BOOLEAN;
        }
        if (identifier.equals("int")) {
            return Type.INT;
        }
        if (identifier.equals("float")) {
            return Type.FLOAT;
        }
        if (identifier.equals("string")) {
            return Type.STRING;
        }
        if (identifier.equals("list")) {
            return Type.LIST;
        }
        if (identifier.equals("dict")) {
            return Type.DICT;
        }
        if (identifier.equals("set")) {
            return Type.SET;
        }
        if (this.context.hasDeclaration(reference)) {
            return Type.INSTANCE;
        }
        return Type.UNKNOWN;
    }
}
