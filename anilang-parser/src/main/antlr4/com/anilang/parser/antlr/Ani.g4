grammar Ani;

file
    :   body EOF
    ;

body
    : (variableDeclarator '\n'*)*
    ;

variableDeclarator
    :   variableDeclaratorId ('=' variableInitializer)?
    ;

variableDeclaratorId
    :   Identifier
    |   Identifier '.' Identifier
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
