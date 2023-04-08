/*
 * Property of ani-lang project.
 */

package com.anilang.context.scope;

import com.anilang.context.impl.PositionKey;
import com.anilang.parser.antlr.AniParser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Create an identifier for a {@link ParserRuleContext} that contains context:
 * <ul>
 *     <li>{@link AniParser.FileContext}</li>
 *     <li>{@link AniParser.ClassDeclarationContext}</li>
 *     <li>{@link AniParser.FuncDeclarationContext}</li>
 *     <li>{@link AniParser.IfStatementContext}</li>
 *     <li>{@link AniParser.IfMainScriptBlockContext}</li>
 *     <li>{@link AniParser.ElseScriptBlockContext}</li>
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
 *
 * @since 0.7.0
 */
public final class FormattedScopedParent {

    /**
     * Context.
     */
    private final ParserRuleContext ctx;

    /**
     * Ctr.
     *
     * @param ctx Context.
     */
    public FormattedScopedParent(final ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    // @checkstyle CyclomaticComplexityCheck (30 lines)
    // @checkstyle ReturnCountCheck (30 lines)
    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public String toString() {
        if (this.ctx instanceof AniParser.FileContext) {
            return "file";
        } else if (this.ctx instanceof AniParser.ClassDeclarationContext) {
            return ((AniParser.ClassDeclarationContext) this.ctx).Identifier().getText();
        } else if (this.ctx instanceof AniParser.FuncDeclarationContext) {
            return ((AniParser.FuncDeclarationContext) this.ctx).Identifier().getText();
        } else if (this.ctx instanceof AniParser.IfStatementContext) {
            return String.format("if%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.IfMainScriptBlockContext) {
            return String.format("if-block%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.ElseScriptBlockContext) {
            return String.format("else-block%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.WhileStatementContext) {
            return String.format("while%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.ForStatementContext) {
            return String.format("for%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.MatchStatementContext) {
            return String.format("match%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.ExpressionLabelCaseContext) {
            return String.format("case-expr%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.IdentifierLabelCaseContext) {
            return String.format("case-id%s", new PositionKey(this.ctx));
        } else if (this.ctx instanceof AniParser.DefaultLabelCaseContext) {
            return String.format("case-default%s", new PositionKey(this.ctx));
        }
        return "";
    }
}
