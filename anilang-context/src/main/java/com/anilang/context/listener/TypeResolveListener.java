/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.impl.ResolveExpressionType;
import com.anilang.context.impl.RuleType;
import com.anilang.context.scope.FormattedScope;
import com.anilang.context.scope.ListParents;
import com.anilang.context.scope.ScopeLookup;
import com.anilang.context.type.RawType;
import com.anilang.context.utils.MetadataFrom;
import com.anilang.context.utils.ScopeKey;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;

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
        final String rawType = new RuleType(rule.type()).raw();
        final String scope = new FormattedScope(
            new ListParents(
                rule.variableDeclaratorId(),
                rule.variableDeclaratorId().Identifier().getText()
            ).asList()
        ).formatted();
        new MetadataFrom(
            context,
            rule.variableDeclaratorId()
        ).ifPresent(
            metadata -> {
                metadata.asType(
                    new RawType(
                        rawType
                    ).type(
                        scope,
                        this.context
                    )
                );
                metadata.setTypeReferenceKey(new ScopeKey(scope, this.context).value());
            }
        );
    }

    @Override
    public void enterFormalParameterDecls(final AniParser.FormalParameterDeclsContext rule) {
        final String rawType = new RuleType(rule.type()).raw();
        final String scope = new ScopeLookup(
            this.context, rawType, rule
        ).scope().formatted();
        new MetadataFrom(
            this.context,
            rule.formalParameterDeclsRest().variableDeclaratorId()
        ).ifPresent(
            metadata -> {
                metadata.asType(new RawType(rawType).type(scope, this.context));
                metadata.setTypeReferenceKey(new ScopeKey(scope, this.context).value());
            }
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
        final String rawType = rule.Identifier().getText();
        final String scope = new ScopeLookup(
            this.context, rawType, rule
        ).scope().formatted();
        new MetadataFrom(
            this.context,
            rule
        ).ifPresent(
            metadata -> {
                metadata.asType(new RawType(rawType).type(scope, this.context));
                metadata.setTypeReferenceKey(new ScopeKey(scope, this.context).value());
            }
        );
    }

    @Override
    public void enterFuncDeclaration(final AniParser.FuncDeclarationContext rule) {
        final AniParser.FuncReturnTypeDeclarationContext returnType =
            rule.funcReturnTypeDeclaration();
        if (returnType != null) {
            final String rawType = new RuleType(returnType.type()).raw();
            final String scope = new ScopeLookup(
                this.context, rawType, rule
            ).scope().formatted();
            new MetadataFrom(
                this.context,
                rule
            ).ifPresent(
                metadata -> {
                    metadata.asType(new RawType(rawType).type(scope, this.context));
                    metadata.setTypeReferenceKey(new ScopeKey(scope, this.context).value());
                }
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
}
