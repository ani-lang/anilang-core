/*
 * Property of ani-lang project.
 */

package com.anilang.context.impl;

import com.anilang.context.AniContext;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class ProgramContext {
    // TODO move to an interface
    // #144

    private final Map<String, AniContext> map;
    private final Path root;

    public ProgramContext(final Path root) {
        this.root = root;
        map = new HashMap<>();
    }

    public AniContext context(final Path path) throws IOException {
        final String key = this.root.relativize(path).toString();
        if (this.map.containsKey(key)) {
            return this.map.get(key);
        }
        final BaseAniContext context = new BaseAniContext(key);
        this.map.put(key, context);
        return context;
    }

    public Map<String, AniContext> asMap() {
        return this.map;
    }
}
