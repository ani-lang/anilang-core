/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.LookupParentContext;
import com.anilang.context.impl.PositionKey;
import com.anilang.context.impl.ReversedScopePath;
import com.anilang.context.impl.ScopePathList;
import com.anilang.context.impl.TypeIdentifier;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;

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
        if (expression instanceof AniParser.MethodCallContext) {
            // TODO method call vs class instantiation
            final AniParser.MethodCallContext methodCall = (AniParser.MethodCallContext) expression;
            final AniParser.ExpressionContext className = methodCall.expression();
            final LookupParentContext lookup = new LookupParentContext(
                this.context,
                className.getText(),
                rule
            );
            final String scope = lookup.getScopeString().orElse("");
            asType(declaratorId, className.getText(), scope);
        }
        if (expression instanceof AniParser.ValueContext) {
            final AniParser.ValueContext value = (AniParser.ValueContext) expression;
            final AniParser.LiteralContext literal = value.primary().literal();
            final String key = new PositionKey(rule).toString();
            if (this.context.contains(key)) {
                final ContextMetadata metadata = this.context.get(key);
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
        }
        if (expression instanceof AniParser.InstancePropertyContext) {
            final AniParser.InstancePropertyContext propertyRule =
                (AniParser.InstancePropertyContext) expression;
            final AniParser.ValueContext value = (AniParser.ValueContext) propertyRule.expression();
            final String varId = value.primary().Identifier().getText();
            final String propertyId = propertyRule.Identifier().getText();
            final LookupParentContext varLookup = new LookupParentContext(
                this.context,
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
                if (this.context.contains(key)) {
                    final ContextMetadata metadata = this.context.get(key);
                    metadata.asType(property.getType());
                    metadata.setTypeReferenceKey(property.getTypeReferenceKey().orElse(""));
                }
            }
        }
        if (expression instanceof AniParser.AdditionOperatorContext) {
            final String key = new PositionKey(rule).toString();
            if (this.context.contains(key)) {
                final ContextMetadata metadata = this.context.get(key);
                metadata.asType(Type.INT);
            }
        }
        if (expression instanceof AniParser.MultiplyOperationContext) {
            final String key = new PositionKey(rule).toString();
            if (this.context.contains(key)) {
                final ContextMetadata metadata = this.context.get(key);
                metadata.asType(Type.INT);
            }
        }
    }

    @Override
    public void enterMethodCall(final AniParser.MethodCallContext rule) {
        final String identifier = rule.expression().getText();
        final LookupParentContext lookup = new LookupParentContext(context, identifier, rule);
        final String scope = lookup.getScopeString().orElse("");
        asType(rule.expression(), identifier, scope);
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
