/*
 * Property of ani-lang project.
 */

package com.anilang.context.program;

import com.anilang.context.AniContext;
import com.anilang.context.Type;
import com.anilang.context.impl.IdentifierType;
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

    @Test
    public void importClasses() throws URISyntaxException, IOException {
        final Path root = Paths.get(
            this.getClass().getResource("/com/anilang/context/program/import_only_class/main.ani").toURI()
        );

        final Program program = new Program(root);
        program.run();
        final ProgramContext programContext = program.context();

        final Map<String, AniContext> map = programContext.asMap();
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue(map.containsKey("main.ani"));
        Assertions.assertTrue(map.containsKey("file.ani"));

        final AniContext fileContext = map.get("file.ani");
        Assertions.assertEquals(3, fileContext.size());
        Assertions.assertEquals("$file$A", fileContext.get("1-0").getScope().formatted());
        Assertions.assertEquals("$file$foo", fileContext.get("5-0").getScope().formatted());
        Assertions.assertEquals("$file$prop", fileContext.get("9-0").getScope().formatted());

        final AniContext mainContext = map.get("main.ani");
        Assertions.assertEquals(2, mainContext.size());
        Assertions.assertEquals("$file$a", mainContext.get("3-0").getScope().formatted());
        Assertions.assertEquals("a", mainContext.get("3-0").name());
        Assertions.assertEquals(Type.INSTANCE, mainContext.get("3-0").getType());
        Assertions.assertEquals(IdentifierType.DECLARATION, mainContext.get("3-0").getIdentifierType());
        Assertions.assertEquals("$file$A", mainContext.get("3-4").getScope().formatted());
        Assertions.assertEquals("A", mainContext.get("3-4").name());
        Assertions.assertEquals(Type.CLASS, mainContext.get("3-4").getType());
        Assertions.assertEquals(IdentifierType.IMPORTED, mainContext.get("3-4").getIdentifierType());
    }

    @Test
    public void importReferences() throws URISyntaxException, IOException {
        final Path root = Paths.get(
            this.getClass().getResource("/com/anilang/context/program/reference/main.ani").toURI()
        );

        final Program program = new Program(root);
        program.run();
        final ProgramContext programContext = program.context();

        final Map<String, AniContext> map = programContext.asMap();
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue(map.containsKey("main.ani"));
        Assertions.assertTrue(map.containsKey("file.ani"));

        final AniContext fileContext = map.get("file.ani");
        Assertions.assertEquals(1, fileContext.size());
        Assertions.assertEquals("$file$A", fileContext.get("1-0").getScope().formatted());

        final AniContext mainContext = map.get("main.ani");
        Assertions.assertEquals(2, mainContext.size());
        Assertions.assertEquals("$file$A", mainContext.get("3-4").getScope().formatted());
        Assertions.assertEquals(IdentifierType.IMPORTED, mainContext.get("3-4").getIdentifierType());
        Assertions.assertEquals("1-0", mainContext.get("3-4").getReference().orElse(""));
        Assertions.assertEquals("file.ani", mainContext.get("3-4").getContextSourceKey().orElse(""));
    }
}
