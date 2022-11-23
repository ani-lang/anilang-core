grammar Ani;

file
    :   body EOF
    ;

body
    :   bodyMember*
    ;

bodyMember
    :   scriptLine
    |   funcDeclaration
    |   '\n'+
    ;

scriptLine
    :   variableDeclarator
    |   statement
    |   expression '(' expressionList? ')'
    |   '\n'+
    ;

funcDeclaration
    :   'def' Identifier formalParameters methodDeclarationRest
    ;

variableDeclarator
    :   variableDeclaratorId '=' variableInitializer
    ;

formalParameters
    :   '(' formalParameterDecls? ')'
    ;

formalParameterDecls
    :   formalParameterDeclsRest
    ;

formalParameterDeclsRest
    :   variableDeclaratorId (',' formalParameterDecls)?
    ;

methodDeclarationRest
    :   methodBody
    ;

methodBody
    :   scriptBlock
    ;

scriptBlock
    :   ':' '\n' scriptLine* '\n' 'end'
    ;

statement
    :   'if' expression elseScriptBlock
    |   'for' forControl scriptBlock
    |   'return' expression?
    ;

elseScriptBlock
    :   ':' '\n' scriptLine* ('else' '\n' scriptLine*)* '\n' 'end'
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
    |   expression 'and' expression
    |   expression 'or' expression
    |   expression '(' expressionList? ')'
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

expressionList
    :   expression (',' expression)*
    ;

DecimalLiteral
    :   ('0' | '1'..'9' '0'..'9'*)
    ;

// toss out whitespace
WS  :   [ \t]+ -> skip ;
