/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.LookupParentContext;
import com.anilang.context.impl.PositionKey;
import com.anilang.context.impl.ResolveExpressionType;
import com.anilang.context.impl.ReversedScopePath;
import com.anilang.context.impl.ScopePathList;
import com.anilang.context.impl.TypeIdentifier;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
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
    public void enterStructBodyMember(final AniParser.StructBodyMemberContext rule) {
        final String typeIdentifier = new TypeIdentifier(rule.type()).toString();
        this.asType(
            rule.type(),
            typeIdentifier,
            new ReversedScopePath(
                new ScopePathList(rule, typeIdentifier).asList()
            ).toString()
        );
    }

    @Override
    public void enterFormalParameterDecls(final AniParser.FormalParameterDeclsContext rule) {
        final String typeIdentifier = new TypeIdentifier(rule.type()).toString();
        this.asType(
            rule.formalParameterDeclsRest().variableDeclaratorId(),
            typeIdentifier,
            new LookupParentContext(this.context, typeIdentifier, rule).getScopeString().orElse("")
        );
    }

    @Override
    public void enterVariableDeclarator(final AniParser.VariableDeclaratorContext rule) {
        final AniParser.VariableDeclaratorIdContext declaratorId = rule.variableDeclaratorId();
        final AniParser.VariableInitializerContext initializer = rule.variableInitializer();
        final AniParser.ExpressionContext expression = initializer.expression();
        new ResolveExpressionType(
            expression
        ).setType(context, declaratorId);

    }

    @Override
    public void enterExpressionInstantiation(final AniParser.ExpressionInstantiationContext rule) {
        final String identifier = rule.Identifier().getText();
        final LookupParentContext lookup = new LookupParentContext(context, identifier, rule);
        final String scope = lookup.getScopeString().orElse("");
        asType(rule, identifier, scope);
    }

    @Override
    public void enterFuncDeclaration(final AniParser.FuncDeclarationContext rule) {
        final AniParser.FuncReturnTypeDeclarationContext returnType =
            rule.funcReturnTypeDeclaration();
        if (returnType != null) {
            final String typeIdentifier = new TypeIdentifier(returnType.type()).toString();
            asType(
                rule,
                typeIdentifier,
                new LookupParentContext(
                    context,
                    typeIdentifier,
                    rule
                ).getScopeString().orElse("")
            );
        }
    }

    @Override
    public void enterReturnStatement(final AniParser.ReturnStatementContext rule) {
        // @checkstyle MethodBodyCommentsCheck (2 lines)
        // TODO must be statementReturn instead of returnStatement
        // #122
        if (rule.expression() != null) {
            new ResolveExpressionType(
                rule.expression()
            ).setType(
                this.context,
                rule.expression()
            );
        }
    }

    /**
     * Resolve the type.
     * TODO 01-03-23 this is duplicated
     *
     * @param rule Rule with the typeIdentifier to set the type.
     * @param typeIdentifier Identifier of the type.
     * @param reference Reference key of the typeIdentifier.
     */
    private void asType(
        final ParserRuleContext rule,
        final String typeIdentifier,
        final String reference
    ) {
        final String key = new PositionKey(rule).toString();
        if (this.context.contains(key)) {
            final ContextMetadata metadata = this.context.get(key);
            metadata.asType(this.getType(typeIdentifier, reference));
            metadata.setTypeReferenceKey(getReferenceKey(reference));
        }
    }

    /**
     * Returns the reference key for an identifier.
     *
     * @param reference Parent key.
     * @return The key of the referenced type. Empty if not found.
     */
    private String getReferenceKey(final String reference) {
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
