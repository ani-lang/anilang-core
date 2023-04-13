/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

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
final class TypeResolveListener extends AniBaseListener {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param context Context.
     */
    TypeResolveListener(final AniContext context) {
        this.context = context;
    }

    @Override
    public void enterStructBodyMember(final AniParser.StructBodyMemberContext rule) {
        final String raw = new RuleType(rule.type()).raw();
        // @checkstyle MethodBodyCommentsCheck (10 lines)
        // TODO remove all String scopes and replace them by Objects
        // #118
        // in this example we can't just replace it by the object since the string is used to be
        // compared and the object generates the String every time it needs it.
        // A cache type scope must be implemented to avoid multiple calculation of the same value.
        final String scope = new FormattedScope(
            new ListParents(
                rule.variableDeclaratorId(),
                rule.variableDeclaratorId().Identifier().getText()
            ).asList()
        ).formatted();
        new MetadataFrom(
            this.context,
            rule.variableDeclaratorId()
        ).ifPresent(
            metadata -> {
                metadata.asType(
                    new RawType(
                        raw
                    ).type(
                        scope,
                        this.context
                    )
                );
                metadata.setReference(new ScopeKey(scope, this.context).value());
            }
        );
    }

    @Override
    public void enterFormalParameterDecls(final AniParser.FormalParameterDeclsContext rule) {
        final String raw = new RuleType(rule.type()).raw();
        final String scope = new ScopeLookup(
            this.context, raw, rule
        ).scope().formatted();
        new MetadataFrom(
            this.context,
            rule.formalParameterDeclsRest().variableDeclaratorId()
        ).ifPresent(
            metadata -> {
                metadata.asType(new RawType(raw).type(scope, this.context));
                metadata.setReference(new ScopeKey(scope, this.context).value());
            }
        );
    }

    @Override
    public void enterVariableDeclarator(final AniParser.VariableDeclaratorContext rule) {
        final AniParser.VariableDeclaratorIdContext declarator = rule.variableDeclaratorId();
        final AniParser.VariableInitializerContext initializer = rule.variableInitializer();
        final AniParser.ExpressionContext expression = initializer.expression();
        new ResolveExpressionType(
            expression
        ).setType(this.context, declarator);
    }

    @Override
    public void enterExpressionInstantiation(final AniParser.ExpressionInstantiationContext rule) {
        final String raw = rule.Identifier().getText();
        final String scope = new ScopeLookup(
            this.context, raw, rule
        ).scope().formatted();
        new MetadataFrom(
            this.context,
            rule
        ).ifPresent(
            metadata -> {
                metadata.asType(new RawType(raw).type(scope, this.context));
                metadata.setReference(new ScopeKey(scope, this.context).value());
            }
        );
    }

    @Override
    public void enterFuncDeclaration(final AniParser.FuncDeclarationContext rule) {
        final AniParser.FuncReturnTypeDeclarationContext type =
            rule.funcReturnTypeDeclaration();
        if (type != null) {
            final String raw = new RuleType(type.type()).raw();
            final String scope = new ScopeLookup(
                this.context, raw, rule
            ).scope().formatted();
            new MetadataFrom(
                this.context,
                rule
            ).ifPresent(
                metadata -> {
                    metadata.asType(new RawType(raw).type(scope, this.context));
                    metadata.setReference(new ScopeKey(scope, this.context).value());
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
