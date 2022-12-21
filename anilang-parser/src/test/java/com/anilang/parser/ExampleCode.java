/*
 * Property of Opencore
 */
package com.anilang.parser;

/**
 * Example file names.
 *
 * @since 0.1.0
 */
public enum ExampleCode {

    /**
     * An invalid example.
     */
    INVALID_ASSIGNATION("invalid_assignation"),

    /**
     * A valid example.
     */
    LITERAL_ASSIGNATION("literal_assignation"),

    /**
     * Invalid Java example.
     */
    JAVA_INVALID("invalid_java"),

    /**
     * Invalid Java example.
     */
    JAVA_VALID("valid_java"),

    /**
     * Valid if statement.
     */
    IF_VALID("if_valid"),

    /**
     * Valid for statement.
     */
    FOR_VALID("for_valid"),

    /**
     * Def fun valid.
     */
    DEF_VALID("def_valid"),

    /**
     * Full syntax example.
     */
    FULL_SYNTAX("full_syntax"),

    /**
     * Valid method calls.
     */
    METHOD_CALL_VALID("method_call_valid"),

    /**
     * While statement.
     */
    WHILE_VALID("while_valid"),

    /**
     * Class.
     */
    CLASS_VALID("class_valid"),

    /**
     * Struct.
     */
    STRUCT_VALID("struct_valid"),

    /**
     * Comment.
     */
    COMMENT_VALID("comment_valid"),

    /**
     * Match.
     */
    MATCH_VALID("match_valid"),

    /**
     * ATM example.
     */
    USE_CASE_ATM("use-case-atm"),

    /**
     * SELECT sql.
     */
    SELECT_SQL("select_sql"),

    /**
     * UPDATE sql.
     */
    UPDATE_SQL("update_sql"),

    /**
     * DELETE sql.
     */
    DELETE_SQL("delete-sql"),

    /**
     * INSERT sql.
     */
    INSERT_SQL("insert-sql"),

    /**
     * Import declaration.
     */
    IMPORT_DECLARATION("import-declaration");

    /**
     * File name.
     */
    private final String name;

    /**
     * Ctor.
     *
     * @param name File name.
     */
    ExampleCode(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
