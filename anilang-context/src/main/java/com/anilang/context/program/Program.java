/*
 * Property of ani-lang project.
 */

package com.anilang.context.program;

import com.anilang.context.AniContext;
import com.anilang.context.analysis.FileAnalysisBuilder;
import com.anilang.context.impl.ProgramContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        runImportedUsage(this.root);
        paths.reset();
        runSelfContainedTypesForPath(this.root);
        paths.reset();
        runCrossContainedTypesForPath(this.root);
        paths.reset();
        runTypeValidationForPath(this.root);
    }

    private void runImportedUsage(final Path base) throws IOException {
        if (paths.contains(base)) {
            // skip if it was already ran over the path
            return;
        }
        this.paths.add(base);
        final AniParser parser = new AniFile(Files.newInputStream(base)).parse();
        final AniContext context = this.context.context(base);
        new FileAnalysisBuilder(context, parser)
            .analyzeImportedUsage()
            .build()
            .run();
        for (final Path path : context.getImports()) {
            runImportedUsage(path);
        }
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
        new FileAnalysisBuilder(context, parser)
            .analyzeImports(this.root.getParent())
            .analyzeDeclaration()
            .analyzeUsageBuilder()
            .build()
            .run();
        for (final Path path : context.getImports()) {
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
        new FileAnalysisBuilder(context, parser)
            .analyzeIdentifierValidation((ctx, string) -> {
            }).build().run();
        for (final Path path : context.getImports()) {
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
        new FileAnalysisBuilder(context, parser)
            .analyzeTypeDefinition()
            .analyzeTypeResolve()
            .build()
            .run();
        for (final Path path : context.getImports()) {
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
        new FileAnalysisBuilder(context, parser)
            .analyzeCrossFileTypeResolve(this.context)
            .build()
            .run();
        for (final Path path : context.getImports()) {
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
        for (final Path path : context.getImports()) {
            runTypeValidationForPath(path);
        }
    }
}
