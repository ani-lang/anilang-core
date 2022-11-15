grammar Ani;

file
    :   body EOF
    ;

body
    :   (line '\n'*)*
    |   '\n'*
    ;

line
    :   variableDeclarator
    |   statement
    ;

variableDeclarator
    :   variableDeclaratorId '=' variableInitializer
    ;

statement
    :   'if' expression ':' '\n' body ('else' '\n' body)? 'end'
    |   'for' forControl ':' '\n' body 'end'
    ;

forControl
    :   itemForControl
    ;

itemForControl
    :   Identifier 'in' expression
    ;

variableDeclaratorId
    :   Identifier
    ;

variableInitializer
    :   expression
    ;

Identifier
    :   [a-zA-Z_]+
    ;

expression
    :   primary
    |   expression '.' Identifier
    |   expression ('+'|'-') expression
    |   expression ('*'|'/') expression
    |   expression ('=' | '!=') expression
    |   expression ('<' '=' | '>' '=' | '<' | '>') expression
    |   expression '&' expression
    |   expression '|' expression
    |   expression '&&' expression
    |   expression '||' expression
    ;

primary
    :   literal
    |   Identifier
    |   '(' expression ')'
    ;

literal
    :   integerLiteral
    ;

integerLiteral
    :   DecimalLiteral
    ;

DecimalLiteral
    :   ('0' | '1'..'9' '0'..'9'*)
    ;

// toss out whitespace
WS  :   [ \t]+ -> skip ;
