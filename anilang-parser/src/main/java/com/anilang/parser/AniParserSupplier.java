package com.anilang.parser;

import com.anilang.parser.antlr.AniLexer;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Basic creation of a {@link AniParser}.
 */
final class AniParserSupplier implements IOCheckedSupplier<AniParser> {

    private final InputStream inputStream;

    /**
     * ctor.
     *
     * @param inputStream source input stream to parse.
     */
    AniParserSupplier(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return AniParser from source.
     */
    @Override
    public AniParser get() throws IOException {
        return new AniParser(
                new CommonTokenStream(
                        new AniLexer(
                                CharStreams.fromStream(inputStream)
                        )
                )
        );
    }
}
