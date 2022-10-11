package com.anilang.parser;

import java.io.InputStream;

public final class ExampleFile {

    private final String exampleName;

    public ExampleFile(final String exampleName) {
        this.exampleName = exampleName;
    }

    public InputStream inputStream() {
        return this.getClass().getResourceAsStream("/com/anilang/parser/" + exampleName + ".ani");
    }
}
