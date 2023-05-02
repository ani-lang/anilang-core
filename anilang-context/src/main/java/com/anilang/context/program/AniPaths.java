/*
 * Property of ani-lang project.
 */

package com.anilang.context.program;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public final class AniPaths {
    // TODO move to interface
    // #144

    /**
     * Initial file. Imports others. Initial point.
     */
    private final Path base;
    /**
     * Involved files through import including the base path.
     */
    private final Set<Path> files;

    public AniPaths(final Path base) {
        this.base = base;
        files = new HashSet<>();
    }

    public void add(final Path path) {
        this.files.add(path);
    }

    public boolean contains(final Path path) {
        return this.files.contains(path);
    }

    public void reset() {
        this.files.clear();
    }
}
