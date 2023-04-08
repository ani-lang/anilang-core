/*
 * Property of ani-lang project.
 */

package com.anilang.context.type;

import com.anilang.context.AniContext;
import com.anilang.context.Type;

/**
 * Represent a raw String {@link Type}.
 *
 * @since 0.7.1
 */
public final class RawType {

    /**
     * The String type.
     */
    private final String rawType;

    /**
     * Ctor.
     *
     * @param rawType Type as String.
     */
    public RawType(final String rawType) {
        this.rawType = rawType;
    }

    /**
     * Resolve the type.
     *
     * @param scope Scope.
     * @param context Context.
     * @return Type.
     * @checkstyle NPathComplexityCheck (35 lines)
     * @checkstyle ReturnCountCheck (35 lines)
     */
    @SuppressWarnings({"PMD.OnlyOneReturn", "PMD.NPathComplexity"})
    public Type type(final String scope, final AniContext context) {
        /* @checkstyle MethodBodyCommentsCheck (10 lines)
         * TODO this must come from the lexer. update the lexer definition
         */
        if (this.rawType.equals("boolean")) {
            return Type.BOOLEAN;
        }
        if (this.rawType.equals("int")) {
            return Type.INT;
        }
        if (this.rawType.equals("float")) {
            return Type.FLOAT;
        }
        if (this.rawType.equals("string")) {
            return Type.STRING;
        }
        if (this.rawType.equals("list")) {
            return Type.LIST;
        }
        if (this.rawType.equals("dict")) {
            return Type.DICT;
        }
        if (this.rawType.equals("set")) {
            return Type.SET;
        }
        if (context.hasDeclaration(scope)) {
            return Type.INSTANCE;
        }
        return Type.UNKNOWN;
    }
}
