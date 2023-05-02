/*
 * Property of ani-lang project.
 */

package com.anilang.context.program;

import com.anilang.context.AniContext;
import com.anilang.context.analysis.DctrArgs;
import com.anilang.context.analysis.FileAnalysisBuilder;
import com.anilang.context.analysis.ImportListener;
import com.anilang.context.impl.ProgramContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public final class Program {
    private final AniPaths paths;
    private final Path root;
    private final ProgramContext context;

    public Program(final Path root) {
        this.root = root;
        paths = new AniPaths(root);
        context = new ProgramContext(this.root.getParent());
    }

    public void run() throws IOException {
        runIdentifiersForPath(this.root);
        paths.reset();
        runIdentifiersValidationForPath(this.root);
        paths.reset();
        runSelfContainedTypesForPath(this.root);
        paths.reset();
        runCrossContainedTypesForPath(this.root);
        paths.reset();
        runTypeValidationForPath(this.root);
    }

    public ProgramContext context() {
        return this.context;
    }

    private void runIdentifiersForPath(final Path base) throws IOException {
        if (paths.contains(base)) {
            // skip if it was already ran over the path
            return;
        }
        this.paths.add(base);
        final AniParser parser = new AniFile(Files.newInputStream(base)).parse();
        final AniContext context = this.context.context(base);
        final DctrArgs args = new FileAnalysisBuilder(context, parser).analyzeDeclaration().analyzeUsageBuilder().build().run();
        List<Path> paths = getImports(args.parser());
        for (final Path path : paths) {
            runIdentifiersForPath(path);
        }
    }

    private void runIdentifiersValidationForPath(final Path base) throws IOException {
        if (paths.contains(base)) {
            // skip if it was already ran over the path
            return;
        }
        paths.add(base);
        final AniParser parser = new AniFile(Files.newInputStream(base)).parse();
        final AniContext context = this.context.context(base);
        new FileAnalysisBuilder(context, parser).analyzeIdentifierValidation((ctx, string) -> {
        }).build().run();
        List<Path> paths = getImports(parser);
        for (final Path path : paths) {
            runIdentifiersValidationForPath(path);
        }
    }

    private void runSelfContainedTypesForPath(final Path base) throws IOException {
        if (paths.contains(base)) {
            // skip if it was already ran over the path
            return;
        }
        paths.add(base);
        final AniParser parser = new AniFile(Files.newInputStream(base)).parse();
        final AniContext context = this.context.context(base);
        new FileAnalysisBuilder(context, parser).analyzeTypeDefinition().analyzeTypeResolve().build().run();
        List<Path> paths = getImports(parser);
        for (final Path path : paths) {
            runSelfContainedTypesForPath(path);
        }
    }

    private void runCrossContainedTypesForPath(final Path base) throws IOException {
        if (paths.contains(base)) {
            // skip if it was already ran over the path
            return;
        }
        paths.add(base);
        final AniParser parser = new AniFile(Files.newInputStream(base)).parse();
        final AniContext context = this.context.context(base);
        new FileAnalysisBuilder(context, parser);
        // TODO implement
        List<Path> paths = getImports(parser);
        for (final Path path : paths) {
            runCrossContainedTypesForPath(path);
        }
    }

    private void runTypeValidationForPath(final Path base) throws IOException {
        if (paths.contains(base)) {
            // skip if it was already ran over the path
            return;
        }
        paths.add(base);
        final AniParser parser = new AniFile(Files.newInputStream(base)).parse();
        final AniContext context = this.context.context(base);
        new FileAnalysisBuilder(context, parser).analyzeTypesValidation(string -> {
        }).build().run();
        List<Path> paths = getImports(parser);
        for (final Path path : paths) {
            runTypeValidationForPath(path);
        }
    }

    private List<Path> getImports(final AniParser parser) {
        parser.reset();
        final LinkedList<String> imports = new LinkedList<>();
        ParseTreeWalker.DEFAULT.walk(new ImportListener(imports), parser.file());
        return imports.stream().map(item -> this.root.getParent().resolve(Paths.get(item))).toList();
    }
}
