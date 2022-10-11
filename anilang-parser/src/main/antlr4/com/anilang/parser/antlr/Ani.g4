grammar Ani;

file
    :   (variableDeclarator '\n'*)*
    ;

variableDeclarator
    :   variableDeclaratorId ('=' variableInitializer)?
    ;


variableDeclaratorId
    :   Identifier
    ;

variableInitializer
    :   expression
    ;

Identifier
    :   [a-zA-Z]+
    ;

expression
    :   primary
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
