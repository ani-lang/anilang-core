/*
 * Property of ani-lang project.
 */

package com.anilang.context;

/**
 * An entry of context.
 *
 * @since 0.7.0
 */
public interface ContextEntry {

    /**
     * Context key.
     *
     * @return Key.
     */
    String getKey();

    /**
     * Metadata.
     *
     * @return Metadata.
     */
    ContextMetadata getValue();
}
