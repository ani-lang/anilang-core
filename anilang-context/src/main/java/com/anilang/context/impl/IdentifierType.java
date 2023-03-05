/*
 * Property of Opencore
 */

package com.anilang.context.impl;

/**
 * Types of identifiers. Each identifier could be a declaration of an item or a reference to an
 * item already declared.
 *
 * @since 0.7.0
 */
public enum IdentifierType {

    /**
     * An item used as reference to a declared item.
     */
    REFERENCE,

    /**
     * An item being declared for the first time.
     */
    DECLARATION
}
