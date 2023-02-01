/*
 * Property of Opencore
 */

package com.anilang.context.impl;

import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Create an identifier for a {@link ParserRuleContext} that contains context:
 * <ul>
 *     <li>{@link AniParser.FileContext}</li>
 *     <li>{@link AniParser.ClassDeclarationContext}</li>
 *     <li>{@link AniParser.FuncDeclarationContext}</li>
 *     <li>{@link AniParser.IfStatementContext}</li>
 *     <li>{@link AniParser.WhileStatementContext}</li>
 *     <li>{@link AniParser.ForStatementContext}</li>
 *     <li>{@link AniParser.MatchStatementContext}</li>
 *     <li>{@link AniParser.ExpressionLabelCaseContext}</li>
 *     <li>{@link AniParser.IdentifierLabelCaseContext}</li>
 *     <li>{@link AniParser.DefaultLabelCaseContext}</li>
 * </ul>
 * Some of these Rules don't have an identifier, ex: if and while. When the Rule is not
 * identifiable we create a unique identifier for it. We don't do this for identifiable Rules
 * because we don't want duplicated identifiers within the same context, so by keeping the
 * identifiers we can prevent this to happen by comparing its context paths.
 * <p>
 * For the rest of rules it returns an empty string.
 */
public final class FormattedScopedParent {

    private final ParserRuleContext ctx;

    public FormattedScopedParent(final ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public String toString() {
        if (ctx instanceof AniParser.FileContext) {
            return "file";
        } else if (ctx instanceof AniParser.ClassDeclarationContext) {
            return ((AniParser.ClassDeclarationContext) ctx).Identifier().getText();
        } else if (ctx instanceof AniParser.FuncDeclarationContext) {
            return ((AniParser.FuncDeclarationContext) ctx).Identifier().getText();
        } else if (ctx instanceof AniParser.IfStatementContext) {
            return "if" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.IfMainScriptBlockContext) {
            return "if-block" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.ElseScriptBlockContext) {
            return "else-block" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.WhileStatementContext) {
            return "while" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.ForStatementContext) {
            return "for" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.MatchStatementContext) {
            return "match" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.ExpressionLabelCaseContext) {
            return "case-expr" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.IdentifierLabelCaseContext) {
            return "case-id" + new PositionKey(ctx);
        } else if (ctx instanceof AniParser.DefaultLabelCaseContext) {
            return "case-default" + new PositionKey(ctx);
        }
        return "";
    }
}
