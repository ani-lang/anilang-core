/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public final class DtrImports implements FileAnalysis {
    private final FileAnalysis origin;
    private final Path root;

    public DtrImports(final FileAnalysis origin, final Path root) {
        this.origin = origin;
        this.root = root;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        final LinkedList<String> imports = new LinkedList<>();
        ParseTreeWalker.DEFAULT.walk(new ImportListener(imports), args.parser().file());
        args.context()
            .setImports(
                imports.stream()
                    .map(item -> this.root.resolve(Paths.get(item)))
                    .toList()
            );
        return args;
    }
}
