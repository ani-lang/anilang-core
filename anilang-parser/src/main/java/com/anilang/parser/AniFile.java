package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Represents an Ani file that can be parsed or scanned for errors.
 * This parser includes a base error listener for errors.
 */
public final class AniFile {

    private final InputStream inputStream;

    public AniFile(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * @return {@link AniParser.FileContext} containing the parsed tree.
     * @throws IOException when source input stream fails.
     */
    public AniParser parse() throws IOException {
        return new UncheckedAniParser(
                inputStream
        ).get();
    }

    public List<ParseError> errors() throws IOException {
        return new AniParserCheck(
                inputStream
        ).errors();
    }
}
