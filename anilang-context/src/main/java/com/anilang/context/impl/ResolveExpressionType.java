/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.scope.Scope;
import com.anilang.context.scope.ScopeLookup;
import com.anilang.context.type.RawType;
import com.anilang.context.utils.MetadataFrom;
import com.anilang.context.utils.ScopeKey;
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
     * Expression.
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

    /**
     * Add the type of the rule to the context once is resolved.
     *
     * @param context Context.
     * @param rule Rule.
     */
    public void setType(final AniContext context, final ParserRuleContext rule) {
        if (this.expression instanceof AniParser.ExpressionInstantiationContext) {
            final AniParser.ExpressionInstantiationContext instance =
                (AniParser.ExpressionInstantiationContext) this.expression;
            final TerminalNode name = instance.Identifier();
            final String scope = new ScopeLookup(
                context,
                name.getText(),
                rule
            ).scope().formatted();
            new MetadataFrom(
                context,
                rule
            ).ifPresent(
                metadata -> {
                    metadata.asType(new RawType(name.getText()).type(scope.formatted(), context));
                    metadata.setReference(new ScopeKey(scope.formatted(), context).value());
                }
            );
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
                final String parentScope = new ScopeLookup(
                    context,
                    value.primary().Identifier().getText(),
                    rule
                ).scope().formatted();

                final String declarationKey = context.getDeclarationKey(parentScope);
                final ContextMetadata declaration = context.get(declarationKey);
                if (context.contains(key) && declaration != null) {
                    final ContextMetadata metadata = context.get(key);
                    metadata.asType(declaration.getType());
                    metadata.setReference(
                        declaration.getReference().orElse("")
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
            final String varScope = new ScopeLookup(
                context,
                varId,
                rule
            ).scope().formatted();
            final String varDeclarationKey = context.getDeclarationKey(varScope);
            final ContextMetadata data = context.get(varDeclarationKey);
            final ContextMetadata type = context.get(data.getReference().orElse(""));
            final String scope = String.format(
                "%s$%s",
                type.getScope().formatted(),
                propertyId
            );
            if (context.hasDeclaration(scope)) {
                final String propertyKey = context.getDeclarationKey(scope);
                final ContextMetadata property = context.get(propertyKey);
                final String key = new PositionKey(rule).toString();
                if (context.contains(key)) {
                    final ContextMetadata metadata = context.get(key);
                    metadata.asType(property.getType());
                    metadata.setReference(property.getReference().orElse(""));
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
            final AniParser.ExpressionMethodCallContext method =
                (AniParser.ExpressionMethodCallContext) this.expression;
            final String scope = new ScopeLookup(
                context,
                method.expression().getText(),
                rule
            ).scope().formatted();
            if (Scope.DEFAULT_SCOPE.equals(scope)) {
                return;
            }
            final String declarationKey = context.getDeclarationKey(scope);
            final ContextMetadata declaration = context.get(declarationKey);
            final String key = new PositionKey(rule).toString();
            if (context.contains(key)) {
                final ContextMetadata metadata = context.get(key);
                metadata.asType(declaration.getType());
                metadata.setReference(
                    declaration.getReference().orElse("")
                );
            }
        }
    }
}
