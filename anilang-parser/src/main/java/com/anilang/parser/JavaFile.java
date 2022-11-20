/*
 * Property of Opencore
 */
package com.anilang.parser;

import com.anilang.parser.antlr.JavaLexer;
import com.anilang.parser.antlr.JavaParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Example of Java file parser.
 *
 * @since 0.2.0
 */
public final class JavaFile {

    /**
     * File source.
     */
    private final InputStream input;

    /**
     * Ctor.
     *
     * @param input Input file.
     */
    public JavaFile(final InputStream input) {
        this.input = input;
    }

    /**
     * Return Java parser tree.
     *
     * @return Parser.
     * @throws IOException When file read fails.
     */
    public JavaParser parse() throws IOException {
        final JavaParser parser = new JavaParser(
            new CommonTokenStream(
                new JavaLexer(
                    CharStreams.fromStream(this.input)
                )
            )
        );
        parser.addErrorListener(new InternalBaseErrorListener());
        return parser;
    }

    /**
     * Return errors found.
     *
     * @return List of errors.
     * @throws IOException When read fails.
     */
    public List<ParseError> errors() throws IOException {
        final JavaParser parser = new JavaParser(
            new CommonTokenStream(
                new JavaLexer(
                    CharStreams.fromStream(this.input)
                )
            )
        );
        final ListErrorListener listener = new ListErrorListener();
        parser.addErrorListener(listener);
        parser.compilationUnit();
        return listener.getErrors();
    }
}
