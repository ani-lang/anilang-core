/*
 * Property of Opencore
 */

package com.anilang.context;

/**
 * Represent the allowed definition types.
 */
public enum Type {
    /**
     * Can be instantiated.
     */
    CLASS,
    /**
     * Have properties.
     */
    STRUCT,
    /**
     * Has been instantiated from an <code>STRUCT</code> or <code>CLASS</code>.
     */
    INSTANCE,
    /**
     * Primitive types.
     */
    BOOLEAN,
    INT,
    FLOAT,
    STRING,
    LIST,
    DICT,
    SET,
    /**
     * Type has not been resolved.
     */
    UNKNOWN
}
