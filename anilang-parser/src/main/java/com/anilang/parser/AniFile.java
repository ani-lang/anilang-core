/*
 * Property of Opencore
 */
package com.anilang.parser;

import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Represents an Ani file that can be parsed or scanned for errors.
 * This parser includes a base error listener for errors.
 *
 * @since 0.1.0
 */
public final class AniFile {

    /**
     * The input stream.
     */
    private final InputStream input;

    /**
     * Ctor.
     *
     * @param input Input source to parse.
     */
    public AniFile(final InputStream input) {
        this.input = input;
    }

    /**
     * Return a parser.
     *
     * @return Parsed tree {@link AniParser.FileContext}.
     * @throws IOException When source input stream fails.
     */
    public AniParser parse() throws IOException {
        return new UncheckedAniParser(
            this.input
        ).get();
    }

    /**
     * Return parser errors.
     *
     * @return List of errors.
     * @throws IOException When input read fails.
     */
    public List<ParseError> errors() throws IOException {
        return new AniParserCheck(
            this.input
        ).errors();
    }
}
