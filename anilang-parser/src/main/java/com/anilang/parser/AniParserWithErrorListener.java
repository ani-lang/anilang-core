package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.BaseErrorListener;

/**
 * Add a listener to the {@link AniParser} provided.
 */
final class AniParserWithErrorListener implements IOCheckedSupplier<AniParser> {
    private final IOCheckedSupplier<AniParser> aniParserSupplier;
    private final BaseErrorListener baseErrorListener;

    /**
     * ctor.
     *
     * @param aniParserSupplier parser supplier.
     * @param baseErrorListener listener to be added to the parser.
     */
    AniParserWithErrorListener(final IOCheckedSupplier<AniParser> aniParserSupplier, final BaseErrorListener baseErrorListener) {
        this.aniParserSupplier = aniParserSupplier;
        this.baseErrorListener = baseErrorListener;
    }

    /**
     * @return adds an error listener to the parser.
     */
    @Override
    public AniParser get() throws IOException {
        final AniParser aniParser = aniParserSupplier.get();
        aniParser.addErrorListener(baseErrorListener);
        return aniParser;
    }
}
