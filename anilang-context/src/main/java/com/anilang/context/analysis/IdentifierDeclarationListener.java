/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.impl.BaseEntry;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;

/**
 * Listen to the rules that define identifiers for a context.
 * It's a down-top search because it's easier to find the parent context than the children context.
 *
 * @since 0.7.0
 */
final class IdentifierDeclarationListener extends AniBaseListener {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Ctor.
     *
     * @param context Context.
     */
    public IdentifierDeclarationListener(final AniContext context) {
        this.context = context;
    }

    @Override
    public void enterStructDeclaration(final AniParser.StructDeclarationContext rule) {
        this.context.addContext(
            new BaseEntry(rule, rule.Identifier().getText())
        );
    }

    @Override
    public void enterClassDeclaration(final AniParser.ClassDeclarationContext rule) {
        this.context.addContext(
            new BaseEntry(rule, rule.Identifier().getText())
        );
    }

    @Override
    public void enterFuncDeclaration(final AniParser.FuncDeclarationContext rule) {
        this.context.addContext(
            new BaseEntry(rule, rule.Identifier().getText())
        );
    }

    @Override
    public void enterVariableDeclaratorId(final AniParser.VariableDeclaratorIdContext rule) {
        // @checkstyle MethodBodyCommentsCheck (1 line)
        // If it's already declared then this is a reference not a declaration.
        if (!this.context.hasDeclaration(rule, rule.Identifier().getText())) {
            this.context.addContext(
                new BaseEntry(
                    rule,
                    rule.Identifier().getText()
                )
            );
        }
    }

    @Override
    public void enterItemForControl(final AniParser.ItemForControlContext rule) {
        this.context.addContext(
            new BaseEntry(rule, rule.Identifier().getText())
        );
    }


}
