/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.CtxPathList;
import com.anilang.context.impl.LookupParentContext;
import com.anilang.context.impl.PositionKey;
import com.anilang.context.impl.ReversedCtxPath;
import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import java.util.Optional;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

// TODO: 28-02-23 resolve types used from class, struct and primitive types
public final class TypeResolveListener extends AniBaseListener {

    private final AniContext aniContext;

    public TypeResolveListener(final AniContext aniContext) {
        this.aniContext = aniContext;
    }

    @Override
    public void enterStructBodyMember(final AniParser.StructBodyMemberContext ctx) {
        asType(
            ctx.type(),
            ctx.Identifier().getText(),
            new ReversedCtxPath(
                new CtxPathList(ctx, ctx.Identifier().getText()).asList()
            ).toString()
        );
    }

    @Override
    public void enterFormalParameterDecls(final AniParser.FormalParameterDeclsContext ctx) {
        final String identifier = Optional.ofNullable(ctx.type().Identifier())
            .map(ParseTree::getText)
            .orElseGet(() -> ctx.type().primitiveType().getText());
        asType(
            ctx.formalParameterDeclsRest().variableDeclaratorId(),
            identifier,
            new LookupParentContext(aniContext, identifier, ctx).getKey().orElse("")
        );
    }

    // TODO: 01-03-23 this is duplicated?
    private void asType(final ParserRuleContext ctx, final String identifier, final String identifierCtxKey) {
        final String key = new PositionKey(ctx).toString();
        if (aniContext.contains(key)) {
            final ContextMetadata contextMetadata = aniContext.get(key);
            contextMetadata.asType(getType(identifier, identifierCtxKey));
        }
    }

    private Type getType(String identifier, String identifierCtxKey) {
        // TODO: 04-03-23 this must come from the lexer. update the lexer definition
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
        if (aniContext.hasDeclaration(identifierCtxKey)) {
            return Type.INSTANCE;
        }
        return Type.UNKNOWN;
    }
}
