/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.impl.BaseEntry;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;

/**
 * Listen to the rules that define identifiers for a context.
 * It's a down-top search because it's easier to find the parent context than the children context.
 */
public final class IdentifierDeclarationListener extends AniBaseListener {

    private final AniContext context;

    public IdentifierDeclarationListener(final AniContext context) {
        this.context = context;
    }

    @Override
    public void enterStructDeclaration(final AniParser.StructDeclarationContext ctx) {
        context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterClassDeclaration(final AniParser.ClassDeclarationContext ctx) {
        context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterSqlDeclarator(final AniParser.SqlDeclaratorContext ctx) {
        context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterFuncDeclaration(final AniParser.FuncDeclarationContext ctx) {
        context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterVariableDeclaratorId(final AniParser.VariableDeclaratorIdContext ctx) {
        context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterItemForControl(final AniParser.ItemForControlContext ctx) {
        context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }
}
