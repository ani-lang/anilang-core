/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Resolve the type for the rule {@link AniParser.ExpressionContext}.
 *
 * @since 0.7.0
 */
public final class ResolveExpressionType {

    /**
     * Expression
     */
    private final AniParser.ExpressionContext expression;

    /**
     * Ctor.
     *
     * @param expression The expression rule.
     */
    public ResolveExpressionType(final AniParser.ExpressionContext expression) {
        this.expression = expression;
    }

    public void setType(final AniContext context, final ParserRuleContext rule) {
        if (this.expression instanceof AniParser.ExpressionInstantiationContext) {
            final AniParser.ExpressionInstantiationContext instantiation =
                (AniParser.ExpressionInstantiationContext) this.expression;
            final TerminalNode className = instantiation.Identifier();
            final LookupParentContext lookup = new LookupParentContext(
                context,
                className.getText(),
                rule
            );
            final String scope = lookup.getScopeString().orElse("");
            asType(rule, className.getText(), scope, context);
        }
        if (this.expression instanceof AniParser.ExpressionValueContext) {
            final AniParser.ExpressionValueContext value = (AniParser.ExpressionValueContext) expression;
            final AniParser.LiteralContext literal = value.primary().literal();
            final String key = new PositionKey(rule).toString();
            if (literal != null) {
                if (context.contains(key)) {
                    final ContextMetadata metadata = context.get(key);
                    if (literal.IntegerLiteral() != null) {
                        metadata.asType(Type.INT);
                    } else if (literal.DecimalLiteral() != null) {
                        metadata.asType(Type.FLOAT);
                    } else if (literal.booleanLiteral() != null) {
                        metadata.asType(Type.BOOLEAN);
                    } else if (literal.StringLiteral() != null) {
                        metadata.asType(Type.STRING);
                    }
                }
            } else if (value.primary().Identifier() != null) {
                final String parentScope = new LookupParentContext(
                    context,
                    value.primary().Identifier().getText(),
                    rule
                ).getScopeString().orElse("");
                final String declarationKey = context.getDeclarationKey(parentScope);
                final ContextMetadata declarationMetadata = context.get(declarationKey);
                if (context.contains(key)) {
                    final ContextMetadata metadata = context.get(key);
                    metadata.asType(declarationMetadata.getType());
                    metadata.setTypeReferenceKey(
                        declarationMetadata.getTypeReferenceKey().orElse("")
                    );
                }
            }
        }
        if (this.expression instanceof AniParser.ExpressionInstancePropertyContext) {
            final AniParser.ExpressionInstancePropertyContext propertyRule =
                (AniParser.ExpressionInstancePropertyContext) this.expression;
            final AniParser.ExpressionValueContext value = (AniParser.ExpressionValueContext) propertyRule.expression();
            final String varId = value.primary().Identifier().getText();
            final String propertyId = propertyRule.Identifier().getText();
            final LookupParentContext varLookup = new LookupParentContext(
                context,
                varId,
                rule
            );
            final String varDeclarationKey =
                context.getDeclarationKey(varLookup.getScopeString().orElse(""));
            final ContextMetadata varData = context.get(varDeclarationKey);
            final ContextMetadata varType = context.get(varData.getTypeReferenceKey().orElse(""));
            final String propertyScopeString = String.format(
                "%s$%s",
                varType.getParents(),
                propertyId
            );
            if (context.hasDeclaration(propertyScopeString)) {
                final String propertyKey = context.getDeclarationKey(propertyScopeString);
                final ContextMetadata property = context.get(propertyKey);
                final String key = new PositionKey(rule).toString();
                if (context.contains(key)) {
                    final ContextMetadata metadata = context.get(key);
                    metadata.asType(property.getType());
                    metadata.setTypeReferenceKey(property.getTypeReferenceKey().orElse(""));
                }
            }
        }
        if (this.expression instanceof AniParser.ExpressionAdditionOperatorContext) {
            final String key = new PositionKey(rule).toString();
            if (context.contains(key)) {
                final ContextMetadata metadata = context.get(key);
                metadata.asType(Type.INT);
            }
        }
        if (this.expression instanceof AniParser.ExpressionMultiplyOperationContext) {
            final String key = new PositionKey(rule).toString();
            if (context.contains(key)) {
                final ContextMetadata metadata = context.get(key);
                metadata.asType(Type.INT);
            }
        }
        if (this.expression instanceof AniParser.ExpressionMethodCallContext) {
            final AniParser.ExpressionMethodCallContext methodCall =
                (AniParser.ExpressionMethodCallContext) this.expression;
            final String parentScope = new LookupParentContext(
                context,
                methodCall.expression().getText(),
                rule
            ).getScopeString().orElse("");
            final String declarationKey = context.getDeclarationKey(parentScope);
            final ContextMetadata declarationMetadata = context.get(declarationKey);
            final String key = new PositionKey(rule).toString();
            if (context.contains(key)) {
                final ContextMetadata metadata = context.get(key);
                metadata.asType(declarationMetadata.getType());
                metadata.setTypeReferenceKey(
                    declarationMetadata.getTypeReferenceKey().orElse("")
                );
            }
        }
    }

    /**
     * Resolve the type.
     * TODO 01-03-23 this is duplicated
     *  @param rule Rule with the typeIdentifier to set the type.
     *
     * @param typeIdentifier Identifier of the type.
     * @param reference Reference key of the typeIdentifier.
     * @param context
     */
    private void asType(
        final ParserRuleContext rule,
        final String typeIdentifier,
        final String reference,
        final AniContext context) {
        final String key = new PositionKey(rule).toString();
        if (context.contains(key)) {
            final ContextMetadata metadata = context.get(key);
            metadata.asType(this.getType(typeIdentifier, reference, context));
            metadata.setTypeReferenceKey(this.getReferenceKey(reference, context));
        }
    }

    /**
     * Returns the reference key for an identifier.
     *
     * @param reference Parent key.
     * @param context
     * @return The key of the referenced type. Empty if not found.
     */
    private String getReferenceKey(final String reference, final AniContext context) {
        if (context.hasDeclaration(reference)) {
            return context.getDeclarationKey(reference);
        }
        return "";
    }

    /**
     * Resolve the type.
     *
     * @param identifier Identifier.
     * @param reference Key.
     * @param context
     * @return Type.
     * @checkstyle NPathComplexityCheck (35 lines)
     * @checkstyle ReturnCountCheck (35 lines)
     */
    @SuppressWarnings({"PMD.OnlyOneReturn", "PMD.NPathComplexity"})
    private Type getType(final String identifier, final String reference, final AniContext context) {
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
        if (context.hasDeclaration(reference)) {
            return Type.INSTANCE;
        }
        return Type.UNKNOWN;
    }
}
