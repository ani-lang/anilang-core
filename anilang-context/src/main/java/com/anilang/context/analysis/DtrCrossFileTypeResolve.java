/*
 * Property of ani-lang project.
 */

package com.anilang.context.analysis;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.Type;
import com.anilang.context.impl.IdentifierType;
import com.anilang.context.impl.ProgramContext;
import java.util.Map;
import java.util.Optional;

public final class DtrCrossFileTypeResolve implements FileAnalysis {
    private final FileAnalysis origin;
    private final ProgramContext program;

    public DtrCrossFileTypeResolve(final FileAnalysis origin, final ProgramContext program) {
        this.origin = origin;
        this.program = program;
    }

    @Override
    public DctrArgs run() {
        final DctrArgs args = this.origin.run();
        final AniContext context = args.context();
        context.asMap().values().forEach(
            metadata -> {
                if (metadata.getType() == Type.UNKNOWN) {
                    programLookup(metadata, context.contextKey())
                        .ifPresent(aniContext -> updateReferences(metadata, aniContext));
                }
            }
        );
        return args;
    }

    private void updateReferences(final ContextMetadata metadata, final AniContext source) {
        final Optional<String> reference = metadata.getReference();
        if (reference.isEmpty()) {
            metadata.setIdentifierType(IdentifierType.IMPORTED);
            final String declarationKey = source.getDeclarationKey("$file$" + metadata.name());
            metadata.setReference(declarationKey);
            ContextMetadata contextMetadata = source.get(declarationKey);
            metadata.asType(contextMetadata.getType());
        }
    }

    private Optional<AniContext> programLookup(
        final ContextMetadata metadata,
        final String exclude
    ) {
        return this.program.asMap()
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey() != exclude && entry.getValue().hasDeclaration("$file$" + metadata.name()))
            .map(Map.Entry::getValue)
            .findFirst();
    }
}
