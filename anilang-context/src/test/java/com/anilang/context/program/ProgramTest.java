/*
 * Property of ani-lang project.
 */

package com.anilang.context.program;

import com.anilang.context.AniContext;
import com.anilang.context.impl.ProgramContext;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProgramTest {
    @Test
    public void importAniFile() throws URISyntaxException, IOException {
        final Path root = Paths.get(
            this.getClass().getResource("/com/anilang/context/program/import/root.ani").toURI()
        );

        final Program program = new Program(root);
        program.run();
        final ProgramContext context = program.context();
        final Map<String, AniContext> map = context.asMap();

        Assertions.assertEquals(3, map.size());
        Assertions.assertTrue(map.containsKey("root.ani"));
        Assertions.assertTrue(map.containsKey("sibling.ani"));
        Assertions.assertTrue(map.containsKey("libs/lib1.ani"));
    }
}
