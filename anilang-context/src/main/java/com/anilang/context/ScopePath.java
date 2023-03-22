/*
 * Property of Opencore
 */

package com.anilang.context;

import java.util.List;

/**
 * Bottom-top sorted parents.
 *
 * @since 0.7.0
 */
public interface ScopePath {

    /**
     * Bottom-top sorted list of parents.
     *
     * @return List of parents.
     */
    List<String> asList();
}
