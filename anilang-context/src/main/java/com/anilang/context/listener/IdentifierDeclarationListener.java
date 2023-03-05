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
 *
 * @since 0.7.0
 */
public final class IdentifierDeclarationListener extends AniBaseListener {

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
    public void enterStructDeclaration(final AniParser.StructDeclarationContext ctx) {
        this.context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterClassDeclaration(final AniParser.ClassDeclarationContext ctx) {
        this.context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterFuncDeclaration(final AniParser.FuncDeclarationContext ctx) {
        this.context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }

    @Override
    public void enterVariableDeclaratorId(final AniParser.VariableDeclaratorIdContext ctx) {
        // @checkstyle MethodBodyCommentsCheck (1 line)
        // If it's already declared then this is a reference not a declaration.
        if (!this.context.hasDeclaration(ctx, ctx.Identifier().getText())) {
            this.context.addContext(
                new BaseEntry(
                    ctx,
                    ctx.Identifier().getText()
                )
            );
        }
    }

    @Override
    public void enterItemForControl(final AniParser.ItemForControlContext ctx) {
        this.context.addContext(
            new BaseEntry(ctx, ctx.Identifier().getText())
        );
    }
}
