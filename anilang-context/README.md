# anilang-context
This module has tools to build a context from an Ani file. This includes validation of types, 
declarations and usage.

# How it works
The steps to build the context are sequencial as follows.

## Declaration
Collects all declared identifiers as classes, methods, variables, structs, etc.

## Usage
Collects all uses of identifiers.

## Validation
Verifies that every identifier used are defined.

## Type definition
Collects all the definition of types as classes and structs.

## Type resolve
Resolves the type of all identifiers based on its definition.
