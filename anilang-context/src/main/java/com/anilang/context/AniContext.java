/*
 * Property of ani-lang project.
 */

package com.anilang.context;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Contains the context of an Ani program.
 *
 * @since 0.7.0
 */
public interface AniContext {

    /**
     * Store an entry.
     *
     * @param entry Entry.
     */
    void addContext(ContextEntry entry);

    /**
     * Validate if a key is saved.
     *
     * @param key Key.
     * @return True if exists, otherwise false.
     */
    boolean contains(String key);

    /**
     * Looks for the metadata related to the key.
     *
     * @param key Key.
     * @return Metadata.
     */
    ContextMetadata get(String key);

    /**
     * Context size.
     *
     * @return Size.
     */
    int size();

    /**
     * Context map.
     *
     * @return Map.
     */
    Map<String, ContextMetadata> asMap();

    /**
     * Look for an identifier within the rule context.
     *
     * @param rule Rule.
     * @param identifier Identifier.
     * @return True if exists, otherwise false.
     */
    boolean hasDeclaration(ParserRuleContext rule, String identifier);

    /**
     * Look for an identifier in the context.
     *
     * @param parent Parent.
     * @return True if exists, otherwise false.
     */
    boolean hasDeclaration(String parent);

    /**
     * Look for the key of the parent.
     *
     * @param parent Parent.
     * @return Key.
     */
    String getDeclarationKey(String parent);

    void setImports(List<Path> imports);

    List<Path> getImports();

    String contextKey();
}
