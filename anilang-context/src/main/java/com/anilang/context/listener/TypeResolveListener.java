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
            new LookupParentContext(this.context, identifier, ctx).getParentKey().orElse("")
        );
    }

    @Override
    public void enterVariableDeclarator(final AniParser.VariableDeclaratorContext rule) {
        final AniParser.VariableDeclaratorIdContext declaratorId = rule.variableDeclaratorId();
        final AniParser.VariableInitializerContext initializer = rule.variableInitializer();
        final AniParser.ExpressionContext expression = initializer.expression();
        if (expression instanceof AniParser.MethodCallContext) {
            final AniParser.MethodCallContext methodCall = (AniParser.MethodCallContext) expression;
            final AniParser.ExpressionContext className = methodCall.expression();
            final LookupParentContext lookup = new LookupParentContext(
                this.context,
                className.getText(),
                rule
            );
            final String parent = lookup.getParentKey().orElse("");
            asType(declaratorId, className.getText(), parent);
        }
    }

    @Override
    public void enterMethodCall(final AniParser.MethodCallContext rule) {
        final String identifier = rule.expression().getText();
        final LookupParentContext lookup = new LookupParentContext(context, identifier, rule);
        final String parents = lookup.getParentKey().orElse("");
        asType(rule.expression(), identifier, parents);
    }

    /**
     * Resolve the type.
     * TODO 01-03-23 this is duplicated
     *
     * @param rule Rule with the identifier to set the type.
     * @param identifier Identifier of the type.
     * @param reference Reference key of the identifier.
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
            metadata.setTypeReferenceKey(getReferenceKey(rule, reference));
        }
    }

    /**
     * Returns the reference key for an identifier.
     *
     * @param rule Rule.
     * @param reference Parent key.
     * @return The key of the referenced type. Empty if not found.
     */
    private String getReferenceKey(final ParserRuleContext rule, final String reference) {
        if (this.context.hasDeclaration(reference)) {
            return this.context.getDeclarationKey(reference);
        }
        return "";
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
