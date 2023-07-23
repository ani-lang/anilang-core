/*
 * Property of ani-lang project.
 */

package com.anilang.context.program;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.impl.ProgramContext;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Lookup for identifiers across files.
 *
 * @since 0.8.0
 */
public final class ProgramContextLookup {

    private final ProgramContext programContext;

    public ProgramContextLookup(final ProgramContext programContext) {
        this.programContext = programContext;
    }

    public Optional<Result> contextOf(final AniContext context, final String identifier) throws IOException {
        for (final Path anImport : context.getImports()) {
            final AniContext aniContext = this.programContext.context(anImport);
            final String scope = "$file$" + identifier;
            if (aniContext.hasDeclaration(scope)) {
                return Optional.of(
                    new Result(
                        aniContext,
                        aniContext.get(aniContext.getDeclarationKey(scope))
                    )
                );
            }
        }
        return Optional.empty();
    }

    public static final class Result {
        private final AniContext context;
        private final ContextMetadata contextMetadata;

        public Result(final AniContext context, final ContextMetadata contextMetadata) {
            this.context = context;
            this.contextMetadata = contextMetadata;
        }

        public AniContext getContext() {
            return context;
        }

        public ContextMetadata getContextMetadata() {
            return contextMetadata;
        }
    }
}
