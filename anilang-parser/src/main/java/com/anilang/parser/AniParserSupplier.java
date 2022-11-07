/*
 * Property of Opencore
 */

/*
 * Property of Opencore
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniLexer;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Basic creation of a {@link AniParser}.
 *
 * @since 0.1.0
 */
final class AniParserSupplier implements IoCheckedSupplier<AniParser> {

    /**
     * The input source.
     */
    private final InputStream input;

    /**
     * Ctor.
     *
     * @param input Source input stream to parse.
     */
    AniParserSupplier(final InputStream input) {
        this.input = input;
    }

    @Override
    public AniParser get() throws IOException {
        final AniParser aniParser = new AniParser(
            new CommonTokenStream(
                new AniLexer(
                    CharStreams.fromStream(this.input)
                )
            )
        );
        aniParser.removeErrorListeners();
        return aniParser;
    }
}
