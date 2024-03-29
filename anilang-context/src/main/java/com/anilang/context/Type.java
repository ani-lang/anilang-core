/*
 * Property of ani-lang project.
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
     * Primitive type.
     */
    BOOLEAN,
    /**
     * Primitive type.
     */
    INT,
    /**
     * Primitive type.
     */
    FLOAT,
    /**
     * Primitive type.
     */
    STRING,
    /**
     * Primitive type.
     */
    LIST,
    /**
     * Primitive type.
     */
    DICT,
    /**
     * Primitive type.
     */
    SET,
    /**
     * Type has not been resolved.
     */
    UNKNOWN
}
