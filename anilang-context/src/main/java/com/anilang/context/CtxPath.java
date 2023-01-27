/*
 * Property of Opencore
 */

package com.anilang.context;

import java.util.List;

public interface CtxPath {

    /**
     * Bottom-top sorted list of parents.
     *
     * @return list of parents.
     */
    List<String> asList();
}
