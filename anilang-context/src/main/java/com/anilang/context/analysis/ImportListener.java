/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.parser.antlr.AniBaseListener;
import com.anilang.parser.antlr.AniParser;
import java.util.List;

public final class ImportListener extends AniBaseListener {
    private final List<String> imports;

    public ImportListener(final List<String> imports) {
        this.imports = imports;
    }

    @Override
    public void enterImportDeclaration(final AniParser.ImportDeclarationContext rule) {
        this.imports.add(rule.StringLiteral().getText().replace("'", ""));
    }
}
