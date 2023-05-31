/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.impl.ProgramContext;
import com.anilang.parser.antlr.AniParser;
import java.io.File;
import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Set up the analysis by steps.
 *
 * @since 0.7.1
 */
public final class FileAnalysisBuilder {

    /**
     * Analysis setup.
     */
    private final FileAnalysis analysis;

    /**
     * Ctor.
     *
     * @param context Context to populate.
     * @param parser Parser.
     */
    public FileAnalysisBuilder(final AniContext context, final AniParser parser) {
        this(new DctrNoAnalysis(context, parser));
    }

    /**
     * Private constructor used to avoid mutability.
     *
     * @param analysis Analysis setup.
     */
    private FileAnalysisBuilder(final FileAnalysis analysis) {
        this.analysis = analysis;
    }

    /**
     * Analyses declaration of identifiers and add them to the provided <code>context</code>.
     *
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeDeclaration() {
        return new FileAnalysisBuilder(
            new DctrDeclarationAnalysis(
                this.analysis
            )
        );
    }

    /**
     * Analyses the usage of identifiers and add them to the provided <code>context</code>.
     *
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeUsageBuilder() {
        return new FileAnalysisBuilder(
            new DctrUsageAnalysis(
                this.analysis
            )
        );
    }

    /**
     * Validates declaration and usage of identifiers.
     * Provides the errors to the consumer if any.
     *
     * @param consumer Accept errors.
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeIdentifierValidation(
        final BiConsumer<ParserRuleContext, String> consumer
    ) {
        return new FileAnalysisBuilder(
            new DctrValidateIdentifiers(
                this.analysis,
                consumer
            )
        );
    }

    /**
     * Analyses type definition and add them to the provided <code>context</code>.
     *
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeTypeDefinition() {
        return new FileAnalysisBuilder(
            new DctrTypeDefinitionAnalysis(
                this.analysis
            )
        );
    }

    /**
     * Analyses the type resolve of identifiers and add them to the provided <code>context</code>.
     *
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeTypeResolve() {
        return new FileAnalysisBuilder(
            new DctrTypeResolveAnalysis(
                this.analysis
            )
        );
    }

    /**
     * Validates the provided path.
     * Provides the errors to the consumer if any.
     *
     * @param path File to validate.
     * @param consumer Accept errors.
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeSourceValidation(
        final Path path,
        final Consumer<String> consumer
    ) {
        return new FileAnalysisBuilder(
            new DctrValidateSource(
                this.analysis,
                path,
                consumer
            )
        );
    }

    /**
     * Validates the types saved in the provided <code>context</code>.
     * Provides the errors to the consumer if any.
     *
     * @param consumer Accept errors.
     * @return FileAnalysisBuilder.
     */
    public FileAnalysisBuilder analyzeTypesValidation(final Consumer<String> consumer) {
        return new FileAnalysisBuilder(
            new DctrValidateTypes(
                this.analysis,
                consumer
            )
        );
    }

    /**
     * The {@link FileAnalysis} setup.
     *
     * @return FileAnalysis.
     */
    public FileAnalysis build() {
        return this.analysis;
    }

    public FileAnalysisBuilder analyzeCrossFileTypeResolve(final ProgramContext context) {
        return new FileAnalysisBuilder(
            new DtrCrossFileTypeResolve(
                this.analysis,
                context
            )
        );
    }

    public FileAnalysisBuilder analyzeImports(final Path root) {
        return new FileAnalysisBuilder(
            new DtrImports(
                this.analysis,
                root
            )
        );
    }
}
